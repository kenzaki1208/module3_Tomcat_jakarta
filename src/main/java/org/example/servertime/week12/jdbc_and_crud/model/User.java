package org.example.servertime.week12.jdbc_and_crud.model;

public class User {
    protected int id;
    protected String username;
    protected String email;
    protected String country;

    public User() {}

    public User(String username, String email, String country) {
        super();
        this.username = username;
        this.email = email;
        this.country = country;
    }

    public User(int id, String username, String email, String country) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
