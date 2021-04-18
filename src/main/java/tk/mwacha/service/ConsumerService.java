package tk.mwacha.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mwacha.dto.Message;

@Service
public class ConsumerService {

    public void action(Message message) throws Exception {
        //throw new Exception("Error");
       System.out.println(message.getText());
    }
}
