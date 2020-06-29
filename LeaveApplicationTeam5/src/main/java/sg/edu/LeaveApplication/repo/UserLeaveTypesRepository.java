package sg.edu.LeaveApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.LeaveApplication.model.UserLeaveTypes;

public interface UserLeaveTypesRepository extends JpaRepository<UserLeaveTypes, Integer> {

}
