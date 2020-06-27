package sg.edu.LeaveApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.LeaveApplication.model.LeaveRecord;

public interface LeaveRepository extends JpaRepository<LeaveRecord, Integer> {

}
