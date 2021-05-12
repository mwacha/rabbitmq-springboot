package tk.mwacha.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tk.mwacha.amqp.EventSender;
import tk.mwacha.alloy.dto.Message;

@Service
@RequiredArgsConstructor
public class RabbitMQService {

    private final EventSender amqp;

    public void sendToConsumer(Message message) {
        amqp.send(message);;
    }
}
