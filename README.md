# Getting Started
docker run -d --hostname my-rabbit --name rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management
### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.4/maven-plugin/reference/html/#build-image)
* [Spring for RabbitMQ](https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#boot-features-amqp)

### Guides
# Abrir o console no docker do rabbitmq
docker exec -it rabbit bash

# Habilitar o plugin p/ mover mensagens na DLQ
rabbitmq-plugins enable rabbitmq_shovel rabbitmq_shovel_management

* [Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/)

