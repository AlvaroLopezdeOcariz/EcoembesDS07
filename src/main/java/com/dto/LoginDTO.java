package com.dto;

public class LoginDTO {

    private String token;
    private String id;
    private String email;

    public LoginDTO() {}

    public LoginDTO(String token, String id, String email) {
        this.token = token;
        this.id = id;
        this.email = email;
    }

    public String getToken() { return token; }
    public String getId() { return id; }
    public String getEmail() { return email; }
}
