package tk.mwacha.service;

import org.springframework.stereotype.Service;

import tk.mwacha.alloy.dto.Message;

@Service
public class ConsumerService {

    public void action(Message message) throws Exception {
        //throw new Exception("Error");
       System.out.println(message.getText());
    }
}
