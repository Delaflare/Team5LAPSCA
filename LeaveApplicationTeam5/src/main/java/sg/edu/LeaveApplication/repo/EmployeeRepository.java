package sg.edu.LeaveApplication.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.LeaveApplication.model.User;

public interface EmployeeRepository extends JpaRepository<User, Integer> {

	public List<User> findAllUserByName(String name);
}
