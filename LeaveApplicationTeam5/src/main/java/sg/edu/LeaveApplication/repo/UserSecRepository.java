package sg.edu.LeaveApplication.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import sg.edu.LeaveApplication.model.User;

public interface UserSecRepository extends CrudRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.userName = :username")
    public User getUserByUserName(@Param("username") String username);
}
