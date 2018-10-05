# Spring Boot Camel REST 3scale QuickStart

This example demonstrates how to use Camel's REST DSL to expose a RESTful API and expose it to 3scale.

This example relies on the [Fabric8 Maven plugin](https://maven.fabric8.io) for its build configuration
and uses the [fabric8 Java base image](https://github.com/fabric8io/base-images#java-base-images).

The Fabric8 Maven Plugin discovers service metadata from Camel XML Context's service definition and exposes the following:
#### Service Label
* `discovery.3scale.net/discoverable`: Allows 3scale to select Services that are to be automatically exposed.

#### Service Annotations
* `discovery.3scale.net/discovery-version`: the version of the 3scale discovery process.
* `discovery.3scale.net/scheme`: this can be http or https
* `discovery.3scale.net/path`: (optional) the contextPath of the service if it's not at the root.
* `discovery.3scale.net/description-path`: (optional) the path to the service description document (OpenAPI/Swagger). The path is either relative or an external full URL.

### Building

The example can be built with:

    $ mvn install

This automatically generates the application resource descriptors and builds the Docker image, so it requires access to a Docker daemon, relying on the `DOCKER_HOST` environment variable by default.

### Running the example locally

The example can be run locally using the following Maven goal:

    $ mvn spring-boot:run

Alternatively, you can run the application locally using the executable JAR produced:

    $ java -jar -Dspring.profiles.active=dev target/spring-boot-camel-rest-3scale-1.0-SNAPSHOT.jar

You can then access the REST API directly from your Web browser, e.g.:

- <http://localhost:8080/camel-rest-3scale/users/greet>
- <http://localhost:8080/camel-rest-3scale/users/list>

### Running the example in Kubernetes / OpenShift

It is assumed a Kubernetes / OpenShift platform is already running. If not, you can find details how to [get started](http://fabric8.io/guide/getStarted/index.html).

The example can be built and deployed using a single goal:

    $ mvn fabric8:run

You can use the Kubernetes or OpenShift client tool to inspect the status, e.g.:

- To list all the running pods:
    ```
    $ kubectl get pods
    ```

- or on OpenShift:
    ```
    $ oc get pods
    ```

- Then find the name of the pod that runs this example, and output the logs from the running pod with:
    ```
    $ kubectl logs <pod_name>
    ```

- or on OpenShift:
    ```
    $ oc logs <pod_name>
    ```

You can also use the Fabric8 [Web console](http://fabric8.io/guide/console.html) to manage the running pods, view logs and much more.

### Accessing the REST service

When the example is running, a REST service is available to list users that have called this service. The user information comes from 3scale extensions to autofill API keys. 

* `"x-data-threescale-name": "app_ids"` for the parameter that represents the application ID.
* `"x-data-threescale-name": "app_keys"` or `"x-data-threescale-name": "user_keys"` for the parameter that represents the application or user key.

Notice: As it depends on your OpenShift setup, the hostname (route) might vary. Verify with `oc get routes` which hostname is valid for you. Add the `-Dfabric8.deploy.createExternalUrls=true` option to your Maven commands if you want it to deploy a Route configuration for the service.

The actual endpoint is using the _context-path_ `camel-rest-3scale/users` and the REST service provides two services:

- `users/greet`: to use 3scale API keys to add a user to this service's list of calling users
- `users/list`: to list all users that have called the service

You can then access these services from your Web browser, e.g.:

- <http://qs-camel-rest-3scale.vagrant.f8/camel-rest-3scale/users/greet>
- <http://qs-camel-rest-3scale.vagrant.f8/camel-rest-3scale/users/list>

### Swagger API

The example provides API documentation of the service using Swagger using the _context-path_ `camel-rest-3scale/openapi.json`. You can access the API documentation from your Web browser at <http://qs-camel-rest-3scale.vagrant.f8/camel-rest-3scale/openapi.json>.

### More details

You can find more details about running this [quickstart](http://fabric8.io/guide/quickstarts/running.html) on the website. This also includes instructions how to change the Docker image user and registry.
