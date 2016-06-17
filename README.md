#Spring Morphia

Spring Mophia provides a simple way to add to your project all the beans that configuration that your project will need to have everything setup with one annotation.

##How to use it

Add as dependency to your project and then Add the following annotation to start using it.

### Step 1
Add this annotation to your configuration class:

```java
@Configuration
@EnableSpringMorphia
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

morphia.entityPackage=your.entity.package
```
