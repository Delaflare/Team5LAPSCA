package sg.edu.LeaveApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.LeaveApplication.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
