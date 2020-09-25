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
> oc set resources deploymentConfig init-delay --limits=cpu=150m,memory=512Mi --requests=cpu=50m,memory=256Mi
```

5. Crear el recurso Horizontal Pod Autoscaler
```sh
> oc create -f hpa.yaml
```

NOTA: Si no contamos con la imagen base openjdk18-openshift, debemos ejecutar el comando: 
```sh
> oc import-image redhat-openjdk-18/openjdk18-openshift --from=registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift --confirm
```
