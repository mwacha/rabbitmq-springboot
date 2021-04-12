package tk.mwacha.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import tk.mwacha.dto.Message;
import tk.mwacha.service.RabbitMQService;

@Controller
@RequiredArgsConstructor
public class AmqpController {


    private final RabbitMQService service;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public void sendToConsumer(@RequestBody Message message) {
        service.sendToConsumer(message);
    }
}
