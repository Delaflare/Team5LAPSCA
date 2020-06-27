package sg.edu.LeaveApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.LeaveApplication.model.LeaveTypes;

public interface LeaveTypeRepository extends JpaRepository<LeaveTypes, Integer> {

}
