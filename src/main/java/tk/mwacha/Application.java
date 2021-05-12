package tk.mwacha;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;


@org.springframework.boot.autoconfigure.SpringBootApplication
@EnableRabbit
@ComponentScan("tk.mwacha")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
