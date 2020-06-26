package sg.edu.LeaveApplication.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import sg.edu.LeaveApplication.model.Department;
import sg.edu.LeaveApplication.repo.DepartmentRepository;

public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentRepository drepo;
	
	@Override
	public ArrayList<Department> findAll() {
		ArrayList<Department>list = (ArrayList<Department>)drepo.findAll();
		return list;
	}

	@Override
	public boolean saveDepartment(Department dept) {
		// TODO Auto-generated method stub
		 if (drepo.save(dept) != null)
		 {
			 return true;
		 }
		 else
		 {
			 return false;
		 }
	
	}

	@Override
	public boolean editDepartment(Department dept) {
		 if (drepo.save(dept) != null)
		 {
			 return true;
		 }
		 else
		 {
			 return false;
		 }
	}

	@Override
	public void deleteDepartment(Department dept) {
		// TODO Auto-generated method stub
		drepo.delete(dept);
	}

	@Override
	public ArrayList<String> findAllDepartmentNames() {
		
		ArrayList<String> dept = drepo.findAllDepartmentNames();
		return dept;
	}

	@Override
	public Department findDeparmentByName(String name) {
		ArrayList<Department> list = (ArrayList <Department>) drepo.findByName(name);
		
		Department dept		 = list.get(0); // get the whole list from 0
		
		return dept;
	}

	@Override
	public Department findDeparmentById(Integer id) {
		Department dept = drepo.findById(id).get();
		return dept ;
	}
	
}

