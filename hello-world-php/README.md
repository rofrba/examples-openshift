## PHP Hello-World Source-to-Image (S2I) demo


1. Fork the project into your local github account.
2. Clone the repository on your machine
```sh
> git clone https://github.com/your_username/examples-openshift
```
2. Create a new branch to save any changes
```sh
> git checkout -b s2i
```
```sh
> git push -u origin s2i
```
3. Create a new project 

```sh
> oc new-project s2i-demo
```

4. Create a new app, based on s2i strategy

```sh
> oc new-app -i php:7.1 --name=php-helloworld  https://github.com/your_username/examples-openshift  --context-dir php-hello-world
```

5. Verify that the build process starts with the oc get pods command
```sh
> oc get pods
```

6. Examine the logs for this build. Use the build pod name for this build, php-helloworld-1-build
```sh
> oc logs --all-containers  -f php-helloworld-1-build
```

7. Review the DeploymentConfig for this application
```sh
> oc describe dc/php-helloworld
```

8. Expose the service
```sh
> oc expose svc php-helloworld
```

9. Find the URL associated with the new route and check if it's works

```sh
> oc get route -o jsonpath='{..spec.host}{"\n"}'
```
10. Edit the index.php file and add:

```sh
print "A change is a coming!\n";
```
Save the file

11. Commit the changes and push the code 
```sh
> git add .
> git commit -m "Changed index page contents"
> git push origin s2i
```

12. Start a new Source-to-image build process and wait for it to build and deploy.
```sh
> oc start-build php-helloworld
> oc logs php-helloworld-2-build -f
```
13. After the second build has completed use the oc get pods command to verify that the new version of the application is running
```sh
> oc get pods 
```

14. Test that the application servers the new content
```sh
> curl -s ${application-route}
```


OPTIONAL: If you need to use a Secret to pull source code from the git repository, you can create a secret and add it to the buildConfig

```sh
oc create secret generic git-credentials --from-literal=username=myUserName --from-literal=password=myPassword
```
```sh
> oc new-app -i php:7.1 --name=php-helloworld  https://github.com/rofrba/examples-openshift  --context-dir php-helloworld --source-secret=git-credentials
```
