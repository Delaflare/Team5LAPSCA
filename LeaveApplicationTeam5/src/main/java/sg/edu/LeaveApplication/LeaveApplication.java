package sg.edu.LeaveApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.LeaveApplication.repo.UserRepository;

@SpringBootApplication
public class LeaveApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LeaveApplication.class, args);
		
	}
 
}
