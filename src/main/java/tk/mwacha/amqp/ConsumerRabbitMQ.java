package tk.mwacha.amqp;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import tk.mwacha.dto.Message;
import tk.mwacha.service.ConsumerService;


@Component
@RequiredArgsConstructor
public class ConsumerRabbitMQ {

   private final ConsumerService service;


    @RabbitListener(queues = "${spring.rabbitmq.request.routing-key.producer}")
    public void consumer(Message message) {

        service.action(message);
    }
}
