package com.schooldiary.model;

public class User {

    private long id;
    private String login;
    private String passwordHash;
    private Role role;

    public User(long id, String login, String passwordHash, Role role) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public long getId() { return id; }
    public String getLogin() { return login; }
    public String getPasswordHash() { return passwordHash; }
    public Role getRole() { return role; }
}