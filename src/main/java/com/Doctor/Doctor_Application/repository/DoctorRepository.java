package com.Doctor.Doctor_Application.repository;

import com.Doctor.Doctor_Application.model.Doctor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoctorRepository extends MongoRepository<Doctor, ObjectId> {
}
