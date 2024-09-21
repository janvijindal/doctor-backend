package com.Doctor.Doctor_Application.repository;

import com.Doctor.Doctor_Application.model.Appointment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppointmentRepository extends MongoRepository<Appointment, ObjectId> {
}
