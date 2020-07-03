package sg.edu.LeaveApplication.service;

import java.time.LocalDate;
import java.util.ArrayList;

import sg.edu.LeaveApplication.model.LeaveRecord;
import sg.edu.LeaveApplication.model.Status;
import sg.edu.LeaveApplication.model.User;

public interface LeaveService {
	public LeaveRecord findLeaveRecordById(Integer id);
	public ArrayList<LeaveRecord> findAll();
	public ArrayList<LeaveRecord> findAllByReportTo(Integer reportToId);
	public ArrayList<LeaveRecord> findAllPendingLeave(Integer reportToId);
	public boolean saveLeave(LeaveRecord leaverecord);
	public boolean Approve(Integer id, String comment);
	public void deleteLeave(LeaveRecord lr);
	public void cancelLeave(LeaveRecord lr);
	public boolean Reject(Integer id, String comment);
	public ArrayList<String> findAllLeaveStatusName();
	public ArrayList<LeaveRecord> findLeaveByEmployeeAndLeave(String keyword, String ltName, Integer reportToId);
	public ArrayList<LeaveRecord> findLeaveHistoryByDate(String keyword,LocalDate startDate,LocalDate endDate, String ltName,Integer int_status, Integer reportToId);
	public ArrayList<LeaveRecord> findLeaveHistory(String keyword , String ltName,Integer int_status, Integer reportToId);
	public ArrayList<LeaveRecord> findOnLeave();
	public ArrayList<LeaveRecord> findByUser(User user);
	public ArrayList<LeaveRecord> findUserCompensationLeave(User user);
}
