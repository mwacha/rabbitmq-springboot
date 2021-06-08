# Getting Started
Executar o comando: docker-compose up e subir a aplicação

Instalar o plugin do stomp no rabbitmq
docker exec -it rabbitmq bash
rabbitmq-plugins enable rabbitmq_stomp
rabbitmq-plugins enable rabbitmq_web_stomp

Configurar o websocket no front com a url: ws://127.0.0.1:15674/ws


### Kong
- Clonar o projeto: git clone git@github.com:Kong/docker-kong.git
- Adicionar a configuação no docker-compose:

- Criar uma kong-net: docker network create kong-net
  .....
  networks:
    kong-net:
        external: true    
  ....
  konga-prepare:
    container_name: konga-prepare
    image: pantsel/konga:latest
    command: "-c prepare -a postgres -u postgresql://kong:kong@db:5432/konga_db"
    networks:
      - kong-net
    restart: on-failure
  depends_on:
    - db

  konga:
      container_name: konga
      image: pantsel/konga:latest
      restart: always
      networks:
        - kong-net
      environment:
        DB_ADAPTER: postgres
        DB_HOST: db
        DB_USER: kong
        DB_PASSWORD: kong
        TOKEN_SECRET: ahfdjgjgf79JKLFHJKh978953kgdfjkl
        DB_DATABASE: konga_db
        NODE_ENV: production
    depends_on:
        - db
  ports:
  - "1337:1337"

### Reference Documentation
For further reference, please consider the following sections:
* [Tutorial Kong](https://www.youtube.com/watch?v=_2GRXgYswhI)
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

