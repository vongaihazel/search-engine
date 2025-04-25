package com.example.searchengine.dto;

public class UserDTO {
    private Long id;
    private String username; // Adjust based on your actual User entity fields

    // Constructors
    public UserDTO() {}

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    // Getters and Setters
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

}