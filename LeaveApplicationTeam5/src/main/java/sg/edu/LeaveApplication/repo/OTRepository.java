package sg.edu.LeaveApplication.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.LeaveApplication.model.OTRecord;

public interface OTRepository extends JpaRepository<OTRecord, Integer> {
//	
//	@Query("Select l from otrecord l")
//	ArrayList<OTRecord> findAll();
	
	@Query("Select l from OTRecord l where l.status=0")
	ArrayList<OTRecord> findAllPendingOT();
}
