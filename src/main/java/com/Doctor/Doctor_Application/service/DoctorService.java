package com.Doctor.Doctor_Application.service;

import com.Doctor.Doctor_Application.model.Doctor;
import com.Doctor.Doctor_Application.repository.DoctorRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DoctorService {

     @Autowired
     private DoctorRepository doctorRepository;


    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(ObjectId id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public Doctor saveDoctor(Doctor doctor) throws IOException {
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(ObjectId id) {
        doctorRepository.deleteById(id);
    }

    
}
