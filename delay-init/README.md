## Servicio de simulación delay en init

1. Crear el proyecto

```sh
> oc new-project init-delay
```

2. Crear la aplicación, utilizando S2i

```sh
> oc new-app --name init-delay -i openjdk18-openshift https://github.com/rofrba/examples-openshift --context-dir delay-init
```

3. Exponer el servicio
```sh
> oc expose svc init-delay
```

4. Configurar los recursos de la aplicación
```sh
> oc set resources deploymentConfig init-delay --limits=cpu=200m,memory=512Mi --requests=cpu=100m,memory=256Mi
```

5. Creamos el liveness probe para validar el funcionamiento de la aplicación
```sh
> oc set probe dc/init-delay --liveness --get-url=http://:8080/ --initial-delay-seconds=60 --timeout-seconds=1 --period-seconds=30
```
6. Creamos el readiness probe para validar la comunicación con el servicio slave
```sh
oc set probe dc/init-delay --readiness --get-url=http://:8080/ --initial-delay-seconds=60 --timeout-seconds=1 --period-seconds=30
```

7. Crear el recurso Horizontal Pod Autoscaler
```sh
> oc create -f hpa.yaml
```

NOTA: Si no contamos con la imagen base openjdk18-openshift, debemos ejecutar el comando: 
```sh
> oc import-image redhat-openjdk-18/openjdk18-openshift --from=registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift --confirm
```
