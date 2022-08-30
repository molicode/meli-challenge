# Challenge Backend MeLi

## Demo:

![Challenge - Animated gif demo](demo/meli_demo01.gif)

#### URL: http://localhost:8080/swagger-ui.html#/

## Prerequisites & Versions:

| Java | Maven |  Docker  | Docker-compose | Spring Boot | 
|:----:|:-----:|:--------:|:--------------:|:-----------:|
|  11  | 3.6.3 | 20.10.14 |     1.29.2     |    2.7.2    |



## Set Environment

````
Main class: com.meli.challenge.ChallengeApplication
````


## Installation:

````
git clone https://github.com/molicode/meli-challenge.git
````
Can be opened using Intellij Idea or STS, or can be built directly with the syntax
````
mvn clean install
````

## Docker:
#### Build: 
````
docker build -t ip-service .
````
#### Execute:
````
docker run -p 8080:8080  ip-service -d
````


[//]: # (docker ps)

[//]: # (docker images)

[//]: # (docker run)

[//]: # (docker stop)

[//]: # ()
[//]: # ( url: http://localhost:8081/h2-console/)

[//]: # ( url: http://localhost:8081/swagger-ui.html#/)
