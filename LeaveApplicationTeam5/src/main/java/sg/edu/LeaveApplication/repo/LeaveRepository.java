package sg.edu.LeaveApplication.repo;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.LeaveApplication.model.LeaveRecord;
import sg.edu.LeaveApplication.model.Status;
import sg.edu.LeaveApplication.model.User;

public interface LeaveRepository extends JpaRepository<LeaveRecord, Integer> {

	@Query("SELECT l from LeaveRecord l  where l.user = :user")
	ArrayList<LeaveRecord> findByUser(@Param("user") User user);
	
	@Query("Select l from LeaveRecord l JOIN l.user u  WHERE u.reportsTo = :reportToId")
	ArrayList<LeaveRecord> findAllByReportTo(@Param("reportToId") Integer reportToId);

	@Query(value = "SELECT * FROM testtest.leave_record where CURDATE() BETWEEN start_date AND DATE_ADD(start_date, INTERVAL duration DAY)", nativeQuery = true)
	ArrayList<LeaveRecord> findOnLeave();

	@Query("Select l from LeaveRecord l JOIN l.user u  where l.status=0 AND u.reportsTo=:reportToId OR l.status='PENDING' AND u.reportsTo=:reportToId ")
	ArrayList<LeaveRecord> findAllPendingLeave(@Param("reportToId") Integer reportToId);

	@Query("Select distinct l.status from LeaveRecord l")
	ArrayList<String> findAllLeaveStatus();

	@Query("SELECT l from LeaveRecord l JOIN l.user u JOIN l.leaveTypes lt  "
			+ "WHERE l.status=0 AND u.reportsTo=:reportToId "
			+ "AND (:keyword is null OR CONCAT(u.firstName,' ', u.lastName)=:keyword OR u.firstName LIKE %:keyword% OR u.lastName LIKE %:keyword% ) "
			+ "AND (:ltName is null OR lt.leaveName = :ltName)")
	ArrayList<LeaveRecord> findLeaveByEmployeeAndLeave(@Param("keyword") String keyword,
			@Param("ltName") String leaveName, @Param("reportToId") Integer reportToId);

	@Query("SELECT l from LeaveRecord l JOIN l.user u JOIN l.leaveTypes lt  "
			+ "WHERE (:status=-1 OR l.status=:status) "
			+ "AND (:keyword='' OR CONCAT(u.firstName,' ', u.lastName)=:keyword OR u.firstName LIKE %:keyword% OR u.lastName LIKE %:keyword% ) "
			+ "AND (:startDate=''  OR (l.startDate >= :startDate And l.startDate <= :endDate)) "
			+ "AND (:ltName='' OR lt.leaveName = :ltName) "
			+ "AND u.reportsTo=:reportToId")
	ArrayList<LeaveRecord> findLeaveHistoryByDate(@Param("keyword") String keyword,
			@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
			@Param("ltName") String ltName, @Param("status") Integer int_status,
			@Param("reportToId") Integer reportToId);

	@Query("SELECT l from LeaveRecord l JOIN l.user u JOIN l.leaveTypes lt  "
			+ "WHERE (:status=-1 OR l.status=:status) "
			+ "AND (:keyword='' OR  CONCAT(u.firstName,' ', u.lastName)=:keyword OR u.firstName LIKE %:keyword% OR u.lastName LIKE %:keyword% ) "
			+ "AND (:ltName='' OR lt.leaveName = :ltName) " 
			+ "AND u.reportsTo=:reportToId")
	ArrayList<LeaveRecord> findLeaveHistory(@Param("keyword") String keyword, @Param("ltName") String ltName,
			@Param("status") Integer int_status, @Param("reportToId") Integer reportToId);
	
	@Query("SELECT l from LeaveRecord l JOIN l.leaveTypes lt WHERE lt.leaveName = :leaveName AND l.user = :user")
	ArrayList<LeaveRecord> findByUserAndLeaveName(@Param("user")User user, @Param("leaveName") String name);
}
