package com.carolinedenis.projectmanager.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.carolinedenis.projectmanager.models.LoginUser;
import com.carolinedenis.projectmanager.models.Project;
import com.carolinedenis.projectmanager.models.User;
import com.carolinedenis.projectmanager.repositories.UserRepository;
@Service
public class UserService {
	@Autowired
    private UserRepository userRepo;
    
    public UserService(UserRepository userRepo) {
    	this.userRepo = userRepo;
    }
    
    public User register(User newUser, BindingResult result) {
        Optional<User> optionalUser = userRepo.findByEmail(newUser.getEmail());
        if (optionalUser.isPresent()) {
        	result.rejectValue("email", "Matches", "An account with this email already exists");
        }
        if(!newUser.getPassword().equals(newUser.getConfirm())) {
        	result.rejectValue("email", "Matches", "The confirmation must match the password");
        }
        if(result.hasErrors()) {
        	return null;
        }
        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    	newUser.setPassword(hashed);
    	return userRepo.save(newUser);
    }
    
    public User login(LoginUser newLogin, BindingResult result) {
        Optional<User> optionalUser = userRepo.findByEmail(newLogin.getEmail());
        if(!optionalUser.isPresent()) {
        	result.rejectValue("email", "Matches", "User not found");
        	return null;
        }
        User user = optionalUser.get();
        
    	if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
    	    result.rejectValue("password", "Matches", "Invalid Password!");
    	}    
    	if(result.hasErrors()) {
    		return null;
    	}
    	return user;
    	}
    
    public User findById(Long id) {
    	Optional<User> optionalUser = userRepo.findById(id);
    	if(optionalUser.isPresent()) {
    		return optionalUser.get();
    	}
    	return null;
    }

    public User createUser(User user) {
    	return userRepo.save(user);
    }
    
    public List<User> allByProject(Project project) {
    	return userRepo.findAllByProgress(project);
    }
    
    public List<User> allByNoProject(Project project) {
    	return userRepo.findByProgressNotContains(project);
    }
    
    public User updateUser(User user) {
    	return userRepo.save(user);
    }

}
