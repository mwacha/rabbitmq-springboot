package tk.mwacha.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tk.mwacha.dto.Message;
import tk.mwacha.service.AmqpService;

@Controller
public class AmqpController {

    @Autowired
    private AmqpService service;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public void sendToConsumer(@RequestBody Message message) {
        service.sendToConsumer(message);
    }
}
