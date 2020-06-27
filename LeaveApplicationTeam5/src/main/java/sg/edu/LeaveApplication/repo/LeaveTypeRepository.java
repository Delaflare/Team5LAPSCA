package sg.edu.LeaveApplication.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.LeaveApplication.model.LeaveTypes;

public interface LeaveTypeRepository extends JpaRepository<LeaveTypes, Integer> {

	ArrayList<LeaveTypes> findAll();


}
