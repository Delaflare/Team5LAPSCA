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
	LeaveTypeRepository leavetypeRepo;

	@Override
	public ArrayList<LeaveTypes> findAll() {
		ArrayList<LeaveTypes> list = (ArrayList<LeaveTypes>) leavetypeRepo.findAll();
		return list;
	}

	@Override
	public boolean saveLeaveType(LeaveTypes leavetypes) {
		if (leavetypeRepo.save(leavetypes) != null)
			return true;
		else
			return false;
	}

	@Override
	public void deleteLeaveType(LeaveTypes leavetypes) {
		leavetypeRepo.delete(leavetypes);
	}

	@Override
	public ArrayList<String> findAllLeaveTypeNames() {
		return leavetypeRepo.findAllLeaveTypeNames();
	}

	@Override
	public LeaveTypes findLeaveTypesByName(String name) {
		ArrayList<LeaveTypes> list = (ArrayList<LeaveTypes>) leavetypeRepo.findByleaveName(name);
		return list.get(0);
	}

	@Override
	public LeaveTypes findLeaveTypesById(Integer id) {
		return leavetypeRepo.findById(id).get();
	}

}
