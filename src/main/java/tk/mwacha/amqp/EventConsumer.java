package tk.mwacha.amqp;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import tk.mwacha.alloy.dto.Message;
import tk.mwacha.service.ConsumerService;


@Component
@RequiredArgsConstructor
public class EventConsumer {

   private final ConsumerService service;


    @RabbitListener(queues = "${spring.rabbitmq.request.routing-key.producer}")
    public void consumer(Message message) {
        try {
            service.action(message);
        }catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }

    }
}
