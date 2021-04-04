package tk.mwacha.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mwacha.amqp.AmqpProducer;
import tk.mwacha.dto.Message;
import tk.mwacha.service.AmqpService;

@Service
public class RabbitMQService implements AmqpService {

    @Autowired
    private AmqpProducer<Message> amqp;

    @Override
    public void sendToConsumer(Message message) {
        amqp.producer(message);;
    }
}
