package com.raf.restdemo.dto;

import java.util.Date;

public class ManagerDto {

    private Long id;
    private String username;
    private String email;
    private String password;
    private Long birthDate;
    private String firstName;
    private String lastName;
    private Long startedWorking;
    private boolean active;
    private boolean confirmed;
    private String activationCode;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public Long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
    }

    public Long getStartedWorking() {
        return startedWorking;
    }

    public void setStartedWorking(Long startedWorking) {
        this.startedWorking = startedWorking;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}
