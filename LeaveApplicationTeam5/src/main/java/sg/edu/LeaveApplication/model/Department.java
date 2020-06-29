package sg.edu.LeaveApplication.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Department {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	
	@JoinColumn(name="minmum_count")
	private int minmumCount;
	
	@OneToMany(mappedBy="department")
	private Collection<User> users;
	
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Department(String name, Integer minmumCount) {
		super();
		this.name = name;
		this.minmumCount = minmumCount;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMinmumCount() {
		return minmumCount;
	}
	public void setMinmumCount(int minmumCount) {
		this.minmumCount = minmumCount;
	}


	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", minmumCount=" + minmumCount + "]";
	}
	
	
}
