package io.elice.pokeranger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PokeRangerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokeRangerApplication.class, args);
	}

}
