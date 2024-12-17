package ru.task.taskly.model.dto;

public class UserRegistrationDto {
    private String username;
    private String email;
    private String password;
    private String acceptPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRegistrationDto() {

    }

    public UserRegistrationDto(String username, String password, String acceptPassword, String email) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.acceptPassword = acceptPassword;
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

    public String getAcceptPassword() {
        return acceptPassword;
    }

    public void setAcceptPassword(String acceptPassword) {
        this.acceptPassword = acceptPassword;
    }
}
