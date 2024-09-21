package com.Doctor.Doctor_Application.service;

import com.Doctor.Doctor_Application.config.JwtProvider;
import com.Doctor.Doctor_Application.model.User;
import com.Doctor.Doctor_Application.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

     @Autowired
    private UserRepository userRepository;

     public User findUserByJwt(String jwt){
            String email= JwtProvider.getEmailFromToken(jwt);

           return findUserByEmail(email);
     }

     public User findUserByEmail(String email){
           return userRepository.findUserByEmail(email);
     }

     public User findUserById(ObjectId id) throws Exception {
          Optional<User> user=userRepository.findById(id);
          if(user.isEmpty()){
              throw new Exception("No User Found");
          }

          return user.get();
     }


     public void deleteUser(ObjectId id){
            userRepository.deleteById(id);
     }
}
