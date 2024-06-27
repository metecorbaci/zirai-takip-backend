package com.ziraitakip.backend.dto;

public class RegisterResDto {

    private String email;
    private String firstName;
    private String lastName;
    private String activationCode;

    public RegisterResDto(String email, String firstName, String lastName, String activationCode) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.activationCode = activationCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}
