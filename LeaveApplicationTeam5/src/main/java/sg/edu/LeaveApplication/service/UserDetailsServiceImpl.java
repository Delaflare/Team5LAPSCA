package sg.edu.LeaveApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import sg.edu.LeaveApplication.model.User;
import sg.edu.LeaveApplication.repo.UserSecRepository;
import sg.edu.LeaveApplication.security.MyUserDetails;

public class UserDetailsServiceImpl implements UserDetailsService {
	 
    @Autowired
    private UserSecRepository usersecRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = usersecRepository.getUserByUserName(username);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new MyUserDetails(user);
    }
 
}