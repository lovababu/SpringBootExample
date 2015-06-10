# SpringBootExample
Sample Service developed using Spring Boot, Hibernate ORM, H2 inMemory datastore to demonstrate the microservice architecture.<br/>
Added HATEOAS feature. <br/>
Added support for user interactive documentation using Jsondoc api.

#Tech Stack.
  1. <a href="http://projects.spring.io/spring-boot/">Spring Boot.</a>
  2. <a href="http://hibernate.org/orm/" >HibernateORM. </a>
  3. <a href="http://www.h2database.com/html/main.html">H2 Datastore.</a>
  4. <a href="https://spring.io/understanding/HATEOAS">HATEOAS.</a> (Hypermedia as the Engine of Application State).
  5. <a href="http://jsondoc.org/" >JsonDoc.</a>.
  6. <a href="https://github.com/Netflix/feign/blob/master/README.md">Fiegn.</a>
  7. <a href="http://square.github.io/okhttp/">OKHTTP.</a>
  8. <a href="https://gradle.org/">Gradle</a>

#Prerequisites.
 JDK 7 or above. <br/>
 Gradle (for installation refer https://docs.gradle.org/current/userguide/installation.html)
#How to Import into IDE?
 
 Used gradle build tool as dependency management, build and packaging. Go to the cloned/downloaded directory and run the below task.

1. For eclipse lovers.

 >gradlew eclipse 
 
 The above task generates two new files .eclipse and .project under root directory. Now you should be able import the project.

2. For direct running from command tool window.
 >gradlew bootRun
 
  This task builds the application and start up  the server on default port no 8080, you can change it through application.properties have a look.

3. For packaging to deliver.
 >gradlew bootRepackage  OR >gradlew clean build bootRepackage
  
  After successful ./microservicedemo-service/build/distributions/microsericedemo-service-<version>.zip file can be found. To run this directly unzip it you can find ./bin/microservicedemo-service.bat directory to start the server, befor run it do below modification for only windows users.

Replace entire CLASSPATH setting with the "set CLASSPATH=%APP_HOME%\conf;%APP_HOME%\lib\*;" . Since windows has limitation in classpath setting.
This is bug in gradle https://issues.gradle.org/browse/GRADLE-2992 .

#How to access documentation?
 
  http://localhost:8080/jsondoc-ui.html and paste the url http://localhost:8080/jsondoc in the text box and click on GetDocumentation button.

