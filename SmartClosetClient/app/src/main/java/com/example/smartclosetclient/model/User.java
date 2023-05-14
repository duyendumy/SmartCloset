package com.example.smartclosetclient.model;


import java.util.Collection;
import java.util.Date;

public class User {


    private Long userId;
    private String email;
    private String username;
    private String password;

    private Date createdDate;

    private Collection<Role> roles;

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    private Long closetId;

    public User(Long userId, String email, String username, Long closetId) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.closetId = closetId;
    }

    public User(Long userId, String email, String username, String password, Long closetId) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.password = password;
        this.closetId = closetId;
    }
    public User() {
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getClosetId() {
        return closetId;
    }

    public void setClosetId(Long closetId) {
        this.closetId = closetId;
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
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", closetId=" + closetId +
                '}';
    }
}
