package sg.edu.LeaveApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import sg.edu.LeaveApplication.model.User;
import sg.edu.LeaveApplication.repo.UserRepository;

@SpringBootApplication
public class LeaveApplication {

	@Autowired
	UserRepository urepo;
	
	public static void main(String[] args) {
		SpringApplication.run(LeaveApplication.class, args);
		
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	return args -> {
		
		User u1 = new User("first", "last", "user", "pwd123", "abc@email.com");
		urepo.save(u1);
		
		//Department d1 = new Department("Dept1", 2, u1);	
		
	};

	
}}
