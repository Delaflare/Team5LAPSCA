package sg.edu.LeaveApplication.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import sg.edu.LeaveApplication.model.Department;

public interface DepartmentService {
	public ArrayList<Department> findAll();
	
	public boolean saveDepartment (Department dept);
	public boolean editDepartment (Department dept);
	public void deleteDepartment (Department dept);
	public ArrayList<String> findAllDepartmentNames();
	public Department findDeparmentByName(String name);
	public Department findDeparmentById(Integer id);
}
