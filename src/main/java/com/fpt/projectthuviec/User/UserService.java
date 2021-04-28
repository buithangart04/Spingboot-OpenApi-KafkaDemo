package com.fpt.projectthuviec.User;

import com.fpt.projectthuviec.Company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
private UserRepository userRepository;

    // load all user by companyId
    public List<User> loadUserByCompany(long comId){
        return  userRepository.findByCompanyId(comId);
    }
    // getAllUsers
    public List<User> getAllUsers() {

        List<User> users= new ArrayList<>();
        userRepository.findAll()
                .forEach(users::add);
        return users ;
    }
    // get user by id
    public User getUserById(long id) {

        return userRepository.getOne( id);
    }
    // add user to db
    public void addUser(User user) {

        userRepository.save(user);
    }
    public void updateUser(User user){
        userRepository.save(user);
    }
 public void DeactiveUserByCompany(long comId){
        userRepository.DeactiveUserByCompany(comId);
 }
}
