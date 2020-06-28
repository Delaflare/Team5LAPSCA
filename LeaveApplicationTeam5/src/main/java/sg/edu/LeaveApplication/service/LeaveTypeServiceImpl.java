package sg.edu.LeaveApplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.LeaveApplication.model.LeaveTypes;
import sg.edu.LeaveApplication.repo.LeaveTypeRepository;


@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {
	@Autowired
	LeaveTypeRepository leavetyperepo;
	
	@Override
	public ArrayList<LeaveTypes> findAll(){
		return (ArrayList<LeaveTypes>) leavetyperepo.findAll();
	}
	
}
