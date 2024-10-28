package com.Doctor.Doctor_Application.controller;

import com.Doctor.Doctor_Application.model.Appointment;
import com.Doctor.Doctor_Application.model.User;
import com.Doctor.Doctor_Application.service.AppointmentService;
import com.Doctor.Doctor_Application.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    // Create a new appointment
    @PostMapping("/create/{docId}")
    public ResponseEntity<Appointment> createAppointment(
            @RequestBody Appointment appointment,
            @PathVariable ObjectId docId,
            @RequestHeader("Authorization") String token) {

        // Extract User from Authorization token
        String jwt = token.startsWith("Bearer ") ? token.substring(7) : token;
        User user = userService.findUserByJwt(jwt);
        Appointment createdAppointment = appointmentService.createAppointment(appointment, docId, user);
        return ResponseEntity.ok(createdAppointment);
    }

    // Get all appointments for the logged-in user
    @GetMapping("/user")
    public ResponseEntity<List<Appointment>> getAppointmentsByUser(@RequestHeader("Authorization") String token) throws Exception {
        String jwt = token.startsWith("Bearer ") ? token.substring(7) : token;
        User user = userService.findUserByJwt(jwt);
        List<Appointment> appointments = appointmentService.getAppointmentsByUser(user.getId());
        return ResponseEntity.ok(appointments);
    }


    // Delete an appointment by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable ObjectId id ,  @RequestHeader("Authorization") String token) throws Exception {
        String jwt = token.startsWith("Bearer ") ? token.substring(7) : token;
        User user = userService.findUserByJwt(jwt);
        appointmentService.deleteAppointmentById(id,user);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable ObjectId id, @RequestBody Appointment updatedAppointment, @RequestHeader("Authorization") String token) {
        String jwt = token.startsWith("Bearer ") ? token.substring(7) : token;
        User user = userService.findUserByJwt(jwt);
        try {
            appointmentService.updateAppointmentById(id, updatedAppointment, user);
            return ResponseEntity.ok("Appointment updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

     @GetMapping()
    public ResponseEntity<List<Appointment>> getAppointments(){
          List<Appointment> appointments=appointmentService.getAllAppointments();
          return ResponseEntity.ok(appointments);
    }

}

