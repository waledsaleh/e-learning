package com.elearning.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class StudentDTO {
    @Size(max = 20, min = 3, message = "Name must be between 3 and 20 letters")
    @NotEmpty(message = "Please enter name")
    private String name;
    @Email(message = "Invalid Email")
    @NotEmpty(message = "Please enter email")
    private String email;
    @Size(max = 20, min = 5, message = "Invalid username")
    @NotEmpty(message = "Please enter username")
    private String username;
    @Size(max = 20, min = 6, message = "Invalid password")
    @NotEmpty(message = "Please enter password")
    private String password;
    @NotEmpty(message = "Please enter dateOfBirth")
    private String dateOfBirth;
    @NotEmpty(message = "Please enter gender")
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
