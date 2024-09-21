package com.Doctor.Doctor_Application.model;

import com.Doctor.Doctor_Application.domain.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "doctors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
     @Id
     @JsonSerialize(using = ObjectIdSerializer.class)
     private ObjectId id;  // Use ObjectId for the id field

     private String image;  // Store the image path
     private String name;

     public String getContact() {
          return contact;
     }

     public void setContact(String contact) {
          this.contact = contact;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     private String specialty;
     private String experience;
     private String location;
     private String contact;

//     public Doctor( String image, String name, String specialty, String experience, String location, String contact, String email) {
//          this.image = image;
//          this.name = name;
//          this.specialty = specialty;
//          this.experience = experience;
//          this.location = location;
//          this.contact = contact;
//          this.email = email;
//     }

     private String email;




     public ObjectId getId() {
          return id;
     }

     public void setId(ObjectId id) {
          this.id = id;
     }

     public String getImage() {
          return image;
     }

     public void setImage(String image) {
          this.image = image;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public String getSpecialty() {
          return specialty;
     }

     public void setSpecialty(String specialty) {
          this.specialty = specialty;
     }

     public String getExperience() {
          return experience;
     }

     public void setExperience(String experience) {
          this.experience = experience;
     }

     public String getLocation() {
          return location;
     }

     public void setLocation(String location) {
          this.location = location;
     }

}
