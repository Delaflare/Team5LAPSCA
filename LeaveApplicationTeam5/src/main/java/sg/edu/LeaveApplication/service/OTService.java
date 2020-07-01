package sg.edu.LeaveApplication.service;

import java.util.ArrayList;

import sg.edu.LeaveApplication.model.OTRecord;

public interface OTService {
	public ArrayList<OTRecord> findAll();
	public ArrayList<OTRecord> findAllPendingOT();
}
