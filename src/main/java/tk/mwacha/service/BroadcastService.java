package tk.mwacha.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mwacha.amqp.EventSender;
import tk.mwacha.dto.Message;

import java.util.concurrent.CompletableFuture;


@Service
@RequiredArgsConstructor
@Slf4j
public class BroadcastService {


    private final EventSender sender;

	@Transactional(propagation=Propagation.NOT_SUPPORTED, readOnly= true)
	public void sendMessage(Message message) {

		try {
			CompletableFuture.runAsync(() -> {
			    try {
			    	sender.send(message);
				} catch (Exception e) {
					log.info("Erro ao enviar msg stomp. {} ", e.getMessage());
				}
			});
		} catch (Throwable e) {
			log.info("Erro Async ao enviar msg stomp. {} ", e.getMessage());
		}
	}

}
