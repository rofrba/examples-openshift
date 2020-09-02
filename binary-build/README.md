## Generar una imagen dentro de Openshift desde un archivo local

Estructura del proyecto ejemplo:
```
project
│   README.md
│   Dockerfile    
│
└───files
        index.html
```

Pasos:
1-Nos logueamos al cluster

```sh
> oc login ...
```

2-Creamos el proyecto
```sh
> oc new-project binary-build
```

3-Creamos un nuevo BuildConfig con la estrategia docker
```sh
> oc new-build --name test-binary-build --binary --strategy docker
```

4-Nos ubicamos en el directorio de nuestro proyecto donde se encuentra el Dockerfile
```sh
> cd /path/to/project
```

5-Iniciamos el build
```sh
> oc start-build test-binary-build --from-dir .
```

Con estos pasos, ya vamos a tener una imagen personalizada lista para utilizar dentro de Openshift.

OPCIONAL:

6-Creamos nuestra aplicación en base a la imagen pusheada 
```sh
> oc new-app test-binary-build 
```

7-Exponemos el servicio mediante la creación de una ruta
```sh
> oc expose svc/test-binary-build
```
