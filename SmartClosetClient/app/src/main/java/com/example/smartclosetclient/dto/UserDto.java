package com.example.smartclosetclient.dto;

public class UserDto {
    private Long userId;
    private String email;
    private String username;
    private String password;

    public UserDto(Long userId, String email, String username, String password) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UserDto(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public UserDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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



    @Override
    public String toString() {
        return "User [userId=" + userId + ", email=" + email + ", username=" + username + ", password=" + password
                + "]";
    }

}
