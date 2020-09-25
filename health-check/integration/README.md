## Integración de servicios

1. Crear el proyecto

```sh
> oc new-project integration
```

2. Deploy servicio A que simulará el delay (1 segundo como ejemplo)

```sh
> oc new-app --name integration-slave -i openjdk18-openshift https://github.com/rofrba/examples-openshift --context-dir health-check/delay -e DELAY=1
```

3. Creamos la ruta para el servicio
```sh
> oc expose svc integration-slave
```

4. Obtenemos la ruta para configurar el siguiente servicio. Tomar nota de la url generada.
```sh
> curl $(oc get route -o jsonpath='{..spec.host}{"\n"}')
```

5. Creamos la aplicación master que utilizará al slave
```sh
> oc new-app --name integration-master -i openjdk18-openshift https://github.com/rofrba/examples-openshift --context-dir health-check/service-a -e SERVICE_B_URL=<url_paso_4>
```

5. Creamos el liveness probe para validar el funcionamiento de la aplicación
```sh
> oc set probe dc/integration-master --liveness --get-url=http://:8080/ --initial-delay-seconds=2 --timeout-seconds=2
```
6. Creamos el readiness probe para validar la comunicación con el servicio slave
```sh
oc set probe dc/integration-master --readiness --get-url=http://:8080/connect --initial-delay-seconds=2 --timeout-seconds=2
```

7. Exponemos el servicio master y comprobamos el funcionamiento de la aplicacion master accediendo al path /connect

6. Cambiamos el valor de delay del servicio slave
```sh
> oc set env dc/integration-slave DELAY=5
```
7. Notaremos que, después de un tiempo, el servicio master comenzará a tener errores de readiness probe y por ende, no contaremos con el servicio listo para recibir tráfico.

8. Volvemos a cambiar el delay simulado
```sh
> oc set env dc/integration-slave DELAY=1
```

9. Notaremos que, nuevamente el servicio master funciona correctamente.


NOTA: Si no contamos con la imagen base openjdk18-openshift, debemos ejecutar el comando: 
```sh
> oc import-image redhat-openjdk-18/openjdk18-openshift --from=registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift --confirm
```

