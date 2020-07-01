package sg.edu.LeaveApplication.service;

import java.util.ArrayList;

import javax.validation.Valid;

import sg.edu.LeaveApplication.model.OTRecord;

public interface OTService {
	public ArrayList<OTRecord> findAll();
	public ArrayList<OTRecord> findAllPendingOT();
	public void saveOTRecord(@Valid OTRecord otRecord);
	public void cancelOTRecord(@Valid OTRecord otRecord);
	public void deleteOTRecord(@Valid OTRecord otRecord);
	public OTRecord findById(Integer id);
	
}
