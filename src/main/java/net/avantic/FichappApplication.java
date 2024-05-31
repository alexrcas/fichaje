package net.avantic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FichappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FichappApplication.class, args);
	}

}
