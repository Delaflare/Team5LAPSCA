package sg.edu.LeaveApplication.service;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.LeaveApplication.model.OTRecord;
import sg.edu.LeaveApplication.model.Status;
import sg.edu.LeaveApplication.model.User;
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
		
	
	@Override
	public ArrayList<OTRecord> findAllPendingAndUpdatedOT() {
		ArrayList<OTRecord> list = (ArrayList<OTRecord>) OTrepo.findAllPendingAndUpdatedOT();
		return list;
	}
	
	@Override
	public ArrayList<OTRecord> findPendingOTbyUser(String keyword) {
		return OTrepo.findPendingOTByUser(keyword);
	}
	
	@Override
	public void saveOTRecord(@Valid OTRecord otRecord) {
		OTrepo.save(otRecord);
	}

	@Override
	public void cancelOTRecord(@Valid OTRecord otRecord) {
		otRecord.setStatus(Status.CANCELLED);
		 OTrepo.save(otRecord);
		
	}

	@Override
	public void deleteOTRecord(@Valid OTRecord otRecord) {
		 otRecord.setStatus(Status.DELETED);
		 OTrepo.save(otRecord);
	}

	@Override
	public OTRecord findById(Integer id) {
		OTRecord ot = OTrepo.findById(id).get();
		return ot;
	}
	
	@Override
	public ArrayList<OTRecord> findByUser(User user) {
		return OTrepo.findByUser(user);
	}
	

}