## Service to simulate delay in HTTP request

1. Create project

```sh
> oc new-project service-delay
```

2. Create a new app, based on s2i strategy

```sh
> oc new-app --name service-delay -i openjdk18-openshift https://github.com/rofrba/examples-openshift --context-dir health-check/delay -e DELAY=1
```

3. Expose the service
```sh
> oc expose svc service-delay
```

4. Check the API and check the delay 
```sh
> curl $(oc get route -o jsonpath='{..spec.host}{"\n"}')
```


5. Activate readiness and liveness probes for the application.
```sh
> oc set probe dc/service-delay --liveness --get-url=http://:8080/ --initial-delay-seconds=2 --timeout-seconds=2
```
```sh
oc set probe dc/service-delay --readiness --get-url=http://:8080/ --initial-delay-seconds=2 --timeout-seconds=2
```

6. Change the delay value again and verify the pod status
```sh
> oc set env dc/service-delay DELAY=5
```
```sh
> oc get pods
```

* service-delay-4-nsj2k    1/1     Running     0          107s
* service-delay-5-6hs7d    0/1     Running     1          42s
* service-delay-5-deploy   1/1     Running     0          46s

We see that the pod restarts multiple times and never gets ready because the readiness probe gets no response within the allotted timeout

8. Change the delay to pass the readiness probe
```sh
> oc set env dc/service-delay DELAY=1
```

9. Now we see that the new pod was created and is ready to receive traffic

* service-delay-4-nsj2k    0/1     Terminating   0          5m37s
* service-delay-6-deploy   0/1     Completed     0          22s
* service-delay-6-xq9xs    1/1     Running       0          16s




NOTA: Si no contamos con la imagen base openjdk18-openshift, debemos ejecutar el comando: 
```sh
> oc import-image redhat-openjdk-18/openjdk18-openshift --from=registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift --confirm
```