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

Pour vrai c'étais tellement compliqué trouver comment changer les dépendences, j'ai fini par faire un nouveau projet sur spring initializer avec les bonnes dépendances et copier le contenu dans mon pom.

ce post ma le plus aider <https://stackoverflow.com/questions/69880150/how-to-resolve-consul-domain-inside-docker-compose>

Faque pour comprendre ce qu'on doit faire pour ajouter le registry et que ca fonctionne avec nos services
D'abord on ajoute les dépendences et le dependencies manager

Dependences dans le pom.xml

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-discovery</artifactId>
</dependency>
```

Dependencies manager a mettre apres la balise </dependencies>

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>${spring-cloud.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

Changer le code de la balise parent par celui ci

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.0.6</version>
    <relativePath /> <!-- lookup parent from repository -->
</parent>
```

Ajouter cette propriete dans la balise properties (spring cloud version)

```xml
<properties>
    <java.version>17</java.version>
    <spring-cloud.version>2022.0.1</spring-cloud.version>
</properties>
```

dans application properties ajouter ces proprietes (changer ms_community pour le nom de votre service et consul-container pour le nom du container de consul qui est notre registre. ici laisser le comme ca)

```properties
spring.application.name=ms_community
spring.cloud.consul.host=consul-container
spring.cloud.consul.port=8500
spring.cloud.consul.discovery.prefer-ip-address=true
```

Dans le compose.yaml ajouter ceci dans la propriete environnement de votre service

```yaml
- WAIT_FOR_HOSTS=consul-container:8500
```

Dans depends on, ajouter ceci

```yaml
consul-container:
  condition: service_started
```

La majorité des problemes etaient causes par le fait que les services ont besoin que consul soit demarrer et de savoir ou il est. Voir comment jai configurer consul pour afficher son hostname et faire partie du meme reseau que nos services.

Quand vous avez fait les changements, faites les commandes suivantes pour rebuild vos images et les rendre disponible pour docker.

```bash
cd ms_community/
mvn clean install
docker build -t darkseacollective/ms_community:version1.1 .
docker push darkseacollective/ms_community:version1.1
cd ..
cd backend/
docker compose down
docker compose up -d
```

Aller voir au localhost:8500 si consul voit vos services.
