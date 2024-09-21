package com.Doctor.Doctor_Application.repository;


import com.Doctor.Doctor_Application.model.Appointment;
import com.Doctor.Doctor_Application.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findUserByEmail(String email);
}
