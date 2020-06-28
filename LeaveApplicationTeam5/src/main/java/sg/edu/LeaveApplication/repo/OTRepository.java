package sg.edu.LeaveApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.LeaveApplication.model.OTRecord;

public interface OTRepository extends JpaRepository<OTRecord, Integer> {

}
