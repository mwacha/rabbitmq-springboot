package tk.mwacha.service;

import tk.mwacha.dto.Message;

public interface ConsumerService {

    void action(Message message);
}
