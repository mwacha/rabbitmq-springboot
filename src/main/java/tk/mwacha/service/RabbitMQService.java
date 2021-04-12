package tk.mwacha.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tk.mwacha.amqp.ProducerRabbitMQ;
import tk.mwacha.dto.Message;

@Service
@RequiredArgsConstructor
public class RabbitMQService {

    private final ProducerRabbitMQ amqp;

    public void sendToConsumer(Message message) {
        amqp.producer(message);;
    }
}
