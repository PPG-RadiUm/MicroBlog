package blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Calendar;

@SpringBootApplication
public class Application {
		public static MessageRepository messageRepository;
		public static UserRepository userRepository;
		public static Long userIdSession = null;
		private static final Logger log = LoggerFactory.getLogger(Application.class);

		// Make the application executable
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

		// This code is executed in the terminal when the application starts
    @Bean
		public CommandLineRunner demo(UserRepository userRepository, MessageRepository messageRepository) {
			return (args) -> {
				// save a couple of customers
				userRepository.save(new User("Henry Michel", "RD", "Henry-Michel", "HenryMichel", "", ""));
				messageRepository.save(new Message(new Long(1), "Salut les rivieros !", Calendar.getInstance()));

				// fetch all customers
				log.info("Users found with findAll():");
				log.info("-------------------------------");
				for (User user : userRepository.findAll()) {
					log.info(user.toString());
				}
	      log.info("");

				// fetch an individual customer by ID
				User user = userRepository.findOne(1L);
				log.info("User found with findOne(1L):");
				log.info("--------------------------------");
				log.info(user.toString());
	      log.info("");

				// fetch customers by last name
				User user2 = userRepository.findByUserName("Henry Michel");
	      log.info("User found with findByUserName('Henry Michel'):");
				log.info("--------------------------------------------");
				log.info(user2.toString());
	      log.info("");

				// fetch all messages
				log.info("Messages found with findAll():");
				log.info("-------------------------------");
				for (Message message : messageRepository.findAll()) {
					log.info(message.toString());
				}
	      log.info("");

				this.messageRepository = messageRepository;
				this.userRepository = userRepository;
			};
		}
}
