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
	
	@Query("Select l from OTRecord l where l.status=0 OR l.status='PENDING' OR l.status= 4 OR l.status='UPDATED'")
	ArrayList<OTRecord> findAllPendingAndUpdatedOT();
	
	@Query("SELECT l from OTRecord l JOIN l.user u WHERE (u.firstName LIKE %:keyword% OR u.lastName LIKE %:keyword%)"
			+ " AND (l.status=0  OR l.status= 4)")
	ArrayList<OTRecord> findPendingOTByUser(@Param("keyword") String keyword);
}