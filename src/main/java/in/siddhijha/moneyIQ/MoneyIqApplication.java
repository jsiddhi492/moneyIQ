package in.siddhijha.moneyIQ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MoneyIqApplication {

	public static void main(String[] args) { SpringApplication.run(MoneyIqApplication.class, args);
	}

}
