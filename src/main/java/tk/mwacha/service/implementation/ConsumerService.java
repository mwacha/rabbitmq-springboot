package tk.mwacha.service.implementation;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mwacha.dto.Message;

@Service
public class ConsumerService {

    public void action(Message message) {
        System.out.println(message.getText());
    }
}
