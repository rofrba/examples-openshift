## Deploy GO Application

Pasos:
1-Nos logueamos al cluster

```sh
> oc login ...
```

2-Creamos el proyecto
```sh
> oc new-project hello-go
```

3-Creamos nuestra aplicación basado en una estrategia docker
```sh
> oc new-app --name hello-go https://github.com/rofrba/examples-openshift --context-dir hello-go    
```

4-Exponemos el servicio
```sh
> oc expose svc hello-go
```
