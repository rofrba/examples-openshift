## Integración de servicios

1. Crear el proyecto

```sh
> oc new-project integration
```

2. Deploy servicio A que simulará el delay (1 segundo como ejemplo)

```sh
> oc new-app --name integration-slave -i openjdk18-openshift https://github.com/rofrba/examples-openshift --context-dir health-check/delay -e DELAY=1 --as-deployment-config
```

3. Creamos la ruta para el servicio
```sh
> oc expose svc integration-slave
```

4. Obtenemos la ruta para validar el funcionamiento del servicio y luego armamos la variable de entorno que utilizaremos adelante.
```sh
> curl $(oc get route -o jsonpath='{..spec.host}{"\n"}')
> export SERVICE_B_URL='http://'$(oc get route integration-slave -o jsonpath='{..spec.host}{"\n"}')
```

5. Creamos la aplicación master que utilizará al slave
```sh
> oc new-app --name integration-master -i openjdk18-openshift https://github.com/rofrba/examples-openshift --context-dir health-check/service-a -e SERVICE_B_URL=$(SERVICE_B_URL) --as-deployment-config
```

6. Obtenemos la ruta para controlar el funcionamiento del servicio, conectando con el otro componente.
```sh
> curl $(oc get route integration-master -o jsonpath='{..spec.host}{"\n"}')/connect
```

7. Creamos el liveness probe para validar el funcionamiento de la aplicación
```sh
> oc set probe dc/integration-master --liveness --get-url=http://:8080/ --initial-delay-seconds=2 --timeout-seconds=2
```
8. Creamos el readiness probe para validar la comunicación con el servicio slave
```sh
oc set probe dc/integration-master --readiness --get-url=http://:8080/connect --initial-delay-seconds=2 --timeout-seconds=2
```

9. Volvemos a probar el funcionamiento del servicio
```sh
> curl $(oc get route integration-master -o jsonpath='{..spec.host}{"\n"}')/connect
```

10. Cambiamos el valor de delay del servicio slave y limpiamos los pods completados.
```sh
> oc set env dc/integration-slave DELAY=5
> oc delete pod --field-selector=status.phase==Succeeded
```

11. Notaremos que, después de un tiempo, el servicio master comenzará a tener errores de readiness probe y por ende, no contaremos con el servicio listo para recibir tráfico.
```sh
> watch oc get pods
```

12. Volvemos a cambiar el delay simulado
```sh
> oc set env dc/integration-slave DELAY=1
```

13. Notaremos que, nuevamente el servicio master funciona correctamente.


NOTA: Si no contamos con la imagen base openjdk18-openshift, debemos ejecutar el comando: 
```sh
> oc import-image redhat-openjdk-18/openjdk18-openshift --from=registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift --confirm
```

