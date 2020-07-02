package sg.edu.LeaveApplication.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.LeaveApplication.model.OTRecord;
import sg.edu.LeaveApplication.model.User;

public interface OTRepository extends JpaRepository<OTRecord, Integer> {
//	
//	@Query("Select l from otrecord l")
//	ArrayList<OTRecord> findAll();
	
	@Query("Select l from OTRecord l where l.status=0 OR l.status='PENDING'")
	ArrayList<OTRecord> findAllPendingOT();
	
	@Query("Select l from OTRecord l where l.user = :user")
	ArrayList<OTRecord> findByUser(@Param("user") User user);
}