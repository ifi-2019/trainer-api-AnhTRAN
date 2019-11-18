## IFI - TP 4 - JPA & Repositories

### Thi-Ngoc-Anh TRAN, M2-E-Service, Université de Lille

https://juwit.github.io/ifi-2019/cours/04-jpa/04-tp-jpa.html#_d%C3%A9ploiement_chez_heroku

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

- Sur heroku :
    + https://trainer-api-anhtran.herokuapp.com/