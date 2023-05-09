# S4_FinalProject_Microservices
 microservices for account, community, post and search logic in java

## To-Do
Pour utiliser l'environnement de développement en docker, vous devez faire les étapes suivantes.

1. Installer l'extension Dev containers
2. Ctrl-shift-p : Dev Containers: Rebuild and Reopen in Container (Dans le workspace du projet que vous voulez ouvrir, donc après avoir ouvert le projet dans VSCode avec github desktop par exemple)

## Documentation open-api swagger-ui
Utiliser springdoc, plus a jour que springfox : 
    <https://www.baeldung.com/spring-rest-openapi-documentation>
    <https://springdoc.org/v2/>

## Response codes
<https://developer.mozilla.org/en-US/docs/Web/HTTP/Status>

## Simple tutorial for creating api with our setup
Sources:
    <https://www.bezkoder.com/spring-boot-postgresql-example/>
    <https://www.makeuseof.com/rest-api-spring-boot-create/>

## Ressources we used to understand Dockerfile and image building
<https://spring.io/guides/topicals/spring-boot-docker/>

<https://docs.docker.com/engine/reference/commandline/build/>

[Changing java app running port](https://www.baeldung.com/spring-boot-change-port)

[Problems with the maven build in the process - plugin needed](https://stackoverflow.com/questions/36427868/failed-to-execute-goal-org-apache-maven-pluginsmaven-surefire-plugin2-12test)

<https://docs.docker.com/engine/reference/commandline/tag/>

[The importance of checking a service health before running others if they depend on it being started](https://stackoverflow.com/questions/68411932/my-spring-boot-app-container-cant-connect-to-my-postgresql-database)

[Difference between link and depend on](https://www.baeldung.com/ops/docker-compose-links-depends-on)

## How to persist (keep between runs) postgres data from the container
<https://medium.com/codex/how-to-persist-and-backup-data-of-a-postgresql-docker-container-9fe269ff4334>

## Ressources for api gateway ans service registry
<https://hub.docker.com/r/yeasy/nginx-consul-template>

<https://medium.com/swlh/lets-build-microservices-part-iii-20e9e5c780a0>

<https://developer.hashicorp.com/consul/tutorials/load-balancing/load-balancing-nginx>