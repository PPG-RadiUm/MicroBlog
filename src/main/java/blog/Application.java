package blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new User("Henry Michel", "Henry-Michel", "HenryMichel", "", ""));

			// fetch all customers
			log.info("Users found with findAll():");
			log.info("-------------------------------");
			for (User user : repository.findAll()) {
				log.info(user.toString());
			}
            log.info("");

			// fetch an individual customer by ID
			User user = repository.findOne(1L);
			log.info("User found with findOne(1L):");
			log.info("--------------------------------");
			log.info(user.toString());
            log.info("");

			// fetch customers by last name
            log.info("User found with findByUserName('Henry Michel'):");
			log.info("--------------------------------------------");
			for (User hm : repository.findByUserName("Henry Michel")) {
				log.info(hm.toString());
			}
            log.info("");
		};
	}
}
