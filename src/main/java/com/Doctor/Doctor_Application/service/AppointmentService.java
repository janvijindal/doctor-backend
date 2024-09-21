package com.Doctor.Doctor_Application.service;

import com.Doctor.Doctor_Application.model.Appointment;
import com.Doctor.Doctor_Application.model.Doctor;
import com.Doctor.Doctor_Application.model.User;
import com.Doctor.Doctor_Application.repository.AppointmentRepository;
import com.Doctor.Doctor_Application.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

      @Autowired
      private AppointmentRepository appointmentRepository;

      @Autowired
      private DoctorService doctorService;

      @Autowired
      private UserService userService;

      @Autowired
      private UserRepository userRepository;

    // Create a new appointment
    public Appointment createAppointment(Appointment appointment, ObjectId docId,User user) {
        Appointment appointment1=new Appointment();
        Doctor doctor=doctorService.getDoctorById(docId);
        appointment1.setTime(appointment.getTime());
        appointment1.setNote(appointment.getNote());
        appointment1.setDoctor(doctor);
        appointment1.setSlot(appointment.getSlot());
        appointment1.setUser(user);
        Appointment saved=appointmentRepository.save(appointment1);
        user.getAppointments().add(saved);
        userRepository.save(user);
        return saved;
    }


    // Get appointments for a specific user
    public List<Appointment> getAppointmentsByUser(ObjectId userId) throws Exception {
        User user = userService.findUserById(userId);

        // Check if the user has appointments
        if (user.getAppointments() != null && !user.getAppointments().isEmpty()) {
            return user.getAppointments();
        } else {
            throw new Exception("No appointments found for the user.");
        }
    }



    // Delete an appointment by ID
    public void deleteAppointmentById(ObjectId id, User user) throws Exception {
        // Find the appointment by ID
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(id);

        if (appointmentOpt.isEmpty()) {
            throw new Exception("No appointment found");
        }

        Appointment appointment = appointmentOpt.get();

        // Remove the appointment from the user's list
        user.getAppointments().removeIf(app -> app.getId().equals(appointment.getId()));

        // Save the updated user
        userRepository.save(user);

        // Delete the appointment from the appointment repository
        appointmentRepository.deleteById(id);
    }


    public void updateAppointmentById(ObjectId id, Appointment updatedAppointment, User user) throws Exception {
        // Find the existing appointment by ID
        Optional<Appointment> existingAppointmentOpt = appointmentRepository.findById(id);

        if (existingAppointmentOpt.isEmpty()) {
            throw new Exception("No appointment found");
        }

        Appointment existingAppointment = existingAppointmentOpt.get();

        // Check if the user has this appointment using ObjectId
        if (user.getAppointments().stream().noneMatch(app -> app.getId().equals(existingAppointment.getId()))) {
            throw new Exception("You do not have permission to update this appointment");
        }

        // Update the fields of the existing appointment
        existingAppointment.setNote(updatedAppointment.getNote());
        existingAppointment.setTime(updatedAppointment.getTime());
        existingAppointment.setSlot(updatedAppointment.getSlot());

        // Save the updated appointment
        appointmentRepository.save(existingAppointment);
    }



}
