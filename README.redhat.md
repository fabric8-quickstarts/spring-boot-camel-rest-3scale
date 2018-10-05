# Spring Boot and Camel REST / SQL QuickStart

This example demonstrates how to use Camel's REST DSL to expose a RESTful API and expose it to 3scale.

The Fabric8 Maven Plugin at build time discovers service metadata from Camel XML Context's service definition and exposes the following:
#### Service Label
* `discovery.3scale.net/discoverable`: Allows 3scale to select Services that are to be automatically exposed.

#### Service Annotations
* `discovery.3scale.net/discovery-version`: the version of the 3scale discovery process.
* `discovery.3scale.net/scheme`: this can be http or https
* `discovery.3scale.net/path`: (optional) the contextPath of the service if it's not at the root.
* `discovery.3scale.net/description-path`: (optional) the path to the service description document (OpenAPI/Swagger). The path is either relative or an external full URL.

### Building

The example can be built with

    mvn clean install

### Running the example in OpenShift

It is assumed that:
- OpenShift platform is already running, if not you can find details how to [Install OpenShift at your site](https://docs.openshift.com/container-platform/3.3/install_config/index.html).
- Your system is configured for Fabric8 Maven Workflow, if not you can find a [Get Started Guide](https://access.redhat.com/documentation/en/red-hat-jboss-middleware-for-openshift/3/single/red-hat-jboss-fuse-integration-services-20-for-openshift/)

The example can then be built and deployed using:

    $ mvn fabric8:deploy

To list all the running pods:

    oc get pods

Then find the name of the pod that runs this quickstart, and output the logs from the running pods with:

    oc logs <name of pod>

You can also use the OpenShift [web console](https://docs.openshift.com/container-platform/3.3/getting_started/developers_console.html#developers-console-video) to manage the
running pods, and view logs and much more.

### Running via an S2I Application Template

Application templates allow you deploy applications to OpenShift by filling out a form in the OpenShift console that allows you to adjust deployment parameters.  This template uses an S2I source build so that it handle building and deploying the application for you.

First, import the Fuse image streams:

    oc create -f https://raw.githubusercontent.com/jboss-fuse/application-templates/GA/fis-image-streams.json

Then create the quickstart template:

    oc create -f https://raw.githubusercontent.com/jboss-fuse/application-templates/GA/quickstarts/spring-boot-camel-rest-3scale-template.json

Now when you use "Add to Project" button in the OpenShift console, you should see a template for this quickstart.


### Accessing the REST service

When the example is running, a REST service is available to list the books that can be ordered, and as well the order statuses.

Notice: As it depends on your OpenShift setup, the hostname (route) might vary. Verify with `oc get routes` which hostname is valid for you. Add the `-Dfabric8.deploy.createExternalUrls=true` option to your Maven commands if you want it to deploy a Route configuration for the service.

The actual endpoint is using the _context-path_ `camel-rest-3scale/users` and the REST service provides two services:

- `users/greet`: to use 3scale API keys to add a user to this service's list of calling users
- `users/list`: to list all users that have called the service

You can then access these services from your Web browser, e.g.:

- <http://qs-camel-rest-3scale.vagrant.f8/camel-rest-3scale/users/greet>
- <http://qs-camel-rest-3scale.vagrant.f8/camel-rest-3scale/users/list>

### Swagger API

The example provides API documentation of the service using Swagger using the _context-path_ `camel-rest-3scale/openapi.json`. You can access the API documentation from your Web browser at <http://qs-camel-rest-3scale.example.com/camel-rest-3scale/openapi.json>.
