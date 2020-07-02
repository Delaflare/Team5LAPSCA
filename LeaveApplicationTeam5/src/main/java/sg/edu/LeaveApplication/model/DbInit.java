package sg.edu.LeaveApplication.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import sg.edu.LeaveApplication.repo.DepartmentRepository;
import sg.edu.LeaveApplication.repo.LeaveTypeRepository;
import sg.edu.LeaveApplication.repo.UserRepository;

@Service
public class DbInit implements CommandLineRunner {

	@Autowired
	DepartmentRepository drepo;	
	@Autowired
	UserRepository urepo;
	@Autowired
	LeaveTypeRepository ltrepo;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		this.ltrepo.deleteAll();
		this.urepo.deleteAll();
		this.drepo.deleteAll();

		Department d1 = new Department("Engineering", 1);
		Department d2 = new Department("Account", 2);
		User u1 = new User("first","last", "aaa", "123", "aaa@gmail.com", null, Role.MANAGER, Gender.FEMALE, Title.ANALYST, d1);
		User u2 = new User("first","last", "bbb", "234", "bbb@gmail.com",null, Role.ADMIN, Gender.MALE, Title.EXECUTIVE, d1);
		User u3 = new User("first","last", "ccc", "345", "ccc@gmail.com",null, Role.EMPLOYEE, Gender.MALE, Title.EXECUTIVE, d1);
		LeaveTypes lt1 = new LeaveTypes("Annual Leave");
		LeaveTypes lt2 = new LeaveTypes("Compensation Leave");
		LeaveTypes lt3 = new LeaveTypes("Medical Leave");
		LeaveTypes lt4 = new LeaveTypes("Unpaid Leave");
		
		drepo.save(d1);drepo.save(d2);
		urepo.save(u1);urepo.save(u2);urepo.save(u3);
		ltrepo.save(lt1);ltrepo.save(lt2);ltrepo.save(lt3);ltrepo.save(lt4);
	}

}
