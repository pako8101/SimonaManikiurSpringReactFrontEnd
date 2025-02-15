package kamenov.simonamanikiur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SimonaManikiurApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimonaManikiurApplication.class, args);
	}

}
