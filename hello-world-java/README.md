## Java Hello-World Source-to-Image (S2I)

1. Create project

```sh
> oc new-project source-to-image
```

2. Create a new app, based on s2i strategy

```sh
> oc new-app --name source-to-image -i openjdk18-openshift https://github.com/rofrba/examples-openshift --context-dir hello-world-java
```

3. Expose the service
```sh
> oc expose svc source-to-image
```

OPTIONAL: If you need to use a Secret to pull source code from the git repository, you can create a secret and add it to the buildConfig

```sh
oc create secret generic git-credentials --from-literal=username=myUserName --from-literal=password=myPassword
```
```sh
> oc new-app --name source-to-image -i java:8 https://github.com/rofrba/examples-openshift --context-dir hello-world-java --source-secret=git-credentials
```


NOTA: Si no contamos con la imagen base openjdk18-openshift, debemos ejecutar el comando: 
```sh
> oc import-image redhat-openjdk-18/openjdk18-openshift --from=registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift --confirm
```