package tk.mwacha.amqp.implementation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import tk.mwacha.dto.Message;
import tk.mwacha.service.ConsumerService;

@Component
@NoArgsConstructor
public class ConsumerRabbitMQ {

   private ConsumerService service;


    @RabbitListener(queues = "${spring.rabbitmq.request.routing-key.producer}")
    public void consumer(Message message) {

        service.action(message);
    }
}
