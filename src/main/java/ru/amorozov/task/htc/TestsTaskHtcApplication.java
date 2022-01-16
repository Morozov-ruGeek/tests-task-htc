package ru.amorozov.task.htc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.amorozov.task.htc.services.UserService;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@Slf4j
public class TestsTaskHtcApplication implements CommandLineRunner {

	private final UserService userService;

	@Autowired
	public TestsTaskHtcApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		log.info("=====START USER STORAGE APPLICATION=====");

		String userMessage = getCommandFromConsole().toLowerCase();
		String[] token = userMessage.split("\\s", 1);

		SpringApplication.run(TestsTaskHtcApplication.class, token);
	}

	@Override
	public void run(String... token) throws Exception {
		if (token.length == 0) {
            log.info("Up Context for tests");
			return;
		}
			Long studentId = Long.parseLong(token[0]);
			log.info("======{}======", userService.findUserById(studentId));
	}

	public static String getCommandFromConsole() {
		log.info("=====Please enter user id: ");
		Scanner scanner = new Scanner(System.in);
		do {
			if (scanner.hasNextLine()) {
				return scanner.nextLine();
			}
			log.info("Enter user id");
			scanner.nextLine();
		}
		while (true);
	}
}
