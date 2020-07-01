package sg.edu.LeaveApplication.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.LeaveApplication.model.OTRecord;
import sg.edu.LeaveApplication.repo.OTRepository;

@Service
public class OTServiceImpl implements OTService {
	
	@Autowired
	OTRepository OTrepo;
	
	@Override
	public ArrayList<OTRecord> findAll(){
		ArrayList<OTRecord> leavelist = (ArrayList<OTRecord>) OTrepo.findAll();
		return leavelist;
	}
	
	@Override
	public ArrayList<OTRecord> findAllPendingOT() {
		ArrayList<OTRecord> list = (ArrayList<OTRecord>) OTrepo.findAllPendingOT();
		return list;
	}	
}
