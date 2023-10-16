package com.utm.messenger;

import com.utm.messenger.dao.UserDAO;
import com.utm.messenger.user.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MessengerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessengerApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserDAO userDAO){
		return runner ->{
			createUser(userDAO);
		};
	}

	private void createUser(UserDAO userDAO) {
		System.out.println("Creating the user...");
		User temp_user = new User("Liosa", "Andreevich","liosaandreevich","liosandreevich@mail.ru","liohaandrioha123",true);

		System.out.println("Saving the user...");
		userDAO.save(temp_user);

		System.out.println("All done. Generated ID: " + temp_user.getId());
	}




}
