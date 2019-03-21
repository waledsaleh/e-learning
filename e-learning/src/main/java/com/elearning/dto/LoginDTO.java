package com.elearning.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginDTO {
    @Email(message = "Invalid Email")
    @NotEmpty(message = "Please enter email")
    private String email;
    @Size(max = 20, min = 6, message = "Invalid password")
    @NotEmpty(message = "Please enter password")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
