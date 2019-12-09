## IFI - TP 4 - JPA & Repositories

### Thi-Ngoc-Anh TRAN, M2-E-Service, Université de Lille

- https://juwit.github.io/ifi-2019/cours/04-jpa/04-tp-jpa.html
- https://juwit.github.io/ifi-2019/cours/06-security/06-tp-security.html

#### 1. Configuration de Java 12 sur les postes de l’université
Modifier le fichier ~/.bashrc pour y ajouter les lignes suivantes :

> export JAVA_HOME=/usr/lib/jvm/jdk-12.0.1

> export PATH=$JAVA_HOME/bin:$PATH

### 2. Config url :
Dans config de tomcat : deployement -> application context : mettre /


### 3. Pré-requise : 
- java : version >= 12
- maven : version >= 3

### 4. Execution
- Pour exécuter l'application, dans le terminal :

    > mvn spring-boot:run

- Essayer les URLs suivants dans un navigateur (chrome, firefox, ...):
    + http://localhost:8081/trainers/ 
    + http://localhost:8081/trainers/Ash

- Sur terminal, pour tester POST/PUT/DELETE : 
    + curl -X POST localhost:8081/trainers/ -H 'Content-type:application/json' -d '{"name": "Bug Catcher","team": [{"pokemonTypeId": 13, "level": 6},{"pokemonTypeId": 10, "level": 6}]}'
    + curl -X PUT localhost:8081/trainers/Bug%20Catcher -H 'Content-type:application/json' -d '{"name": "Bug Catcher","team": [{"pokemonTypeId": 13, "level": 7},{"pokemonTypeId": 10, "level": 8}]}'
    + curl -X DELETE localhost:8081/trainers/Bug%20Catcher

### 5. Heroku :
- Essayer les URLs suivants dans un navigateur (chrome, firefox, ...)
    + https://trainer-api-atr.herokuapp.com/
    + https://trainer-api-atr.herokuapp.com/trainers/
    + https://trainer-api-atr.herokuapp.com/trainers/Ash
    
- Sur terminal, pour tester POST/PUT/DELETE :
    + curl -X POST https://trainer-api-atr.herokuapp.com/trainers/ -H 'Content-type:application/json' -d '{"name": "Bug Catcher","team": [{"pokemonTypeId": 13, "level": 6},{"pokemonTypeId": 10, "level": 6}]}'
    + curl -X PUT https://trainer-api-atr.herokuapp.com/trainers/Bug%20Catcher -H 'Content-type:application/json' -d '{"name": "Bug Catcher","team": [{"pokemonTypeId": 13, "level": 7},{"pokemonTypeId": 10, "level": 8}]}'
    + curl -X DELETE https://trainer-api-atr.herokuapp.com/trainers/Bug%20Catcher

## 6. Attention the config when :
- working local
    -  uncomment in pom.xml
        ```xml
        <dependency>
           <groupId>com.h2database</groupId>
           <artifactId>h2</artifactId>
        </dependency>
        ```  
     
- working on server (heroku)
    -  uncomment in pom.xml
        ```xml
        <dependency>
            <groupId>org.postgresql</groupId>
           <artifactId>postgresql</artifactId>
        </dependency>
        ```
    - uncomment in application.properties
        ```properties
        spring.datasource.url=jdbc:postgresql://bxhvyoukcen7sn4j8lyj-postgresql.services.clever-cloud.com:5432/bxhvyoukcen7sn4j8lyj
        spring.datasource.username=uqwlidutkgmgzqu71k10
        spring.datasource.password=oXGVzrmn13eFYYZyspAu
        ```