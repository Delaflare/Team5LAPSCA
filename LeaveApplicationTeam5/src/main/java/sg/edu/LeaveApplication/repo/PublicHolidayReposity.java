package sg.edu.LeaveApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.LeaveApplication.model.PublicHolidays;

public interface PublicHolidayReposity  extends JpaRepository<PublicHolidays, Integer>{

}
