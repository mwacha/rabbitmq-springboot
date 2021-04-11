package tk.mwacha.service.implementation;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tk.mwacha.amqp.implementation.ProducerRabbitMQ;
import tk.mwacha.dto.Message;

@Service
@RequiredArgsConstructor
public class RabbitMQService {

    private final ProducerRabbitMQ amqp;

    public void sendToConsumer(Message message) {
        amqp.producer(message);;
    }
}
