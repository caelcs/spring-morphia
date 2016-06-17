#Spring Morphia

[![Build Status](https://travis-ci.org/caelwinner/spring-security-mongo.svg?branch=master)](https://travis-ci.org/caelwinner/spring-security-mongo)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/uk.co.caeldev/spring-security-mongo/badge.png?style=flat)](http://search.maven.org/#search|ga|1|g%3A%22uk.co.caeldev%22%20AND%20a%3A%22spring-security-mongo%22)

Spring Mophia provides a simple way to add to your project all the beans that configuration that your project will need to have everything setup with one annotation.

##How to use it

Add as dependency to your project and then Add the following annotation to start using it.

### Step 1
Add this annotation to your configuration class:

```java
@Configuration
@EnableSpringMorphia(entityPackageScan={"your.entity.packages"})
public class Application {

}
```
Having this annotation will define in your spring context all the necessary to use this library.

### Step 2
define the following properties in your app:

```
mongo.host=localhost
mongo.port=27017
mongo.database=testdb
mongo.username=testuser
mongo.password=testpassword
```
