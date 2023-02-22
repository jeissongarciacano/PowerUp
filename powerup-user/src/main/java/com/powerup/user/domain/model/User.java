package com.powerup.user.domain.model;

public class User {
    private Long id;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private Long idRole;
    private String idDocument;

    public User(Long id, String name, String lastName, String phone, String email,
                String password, Long idRole, String idDocument) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.idRole = idRole;
        this.idDocument = idDocument;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Long getIdRole() {
        return idRole;
    }

    public String getIdDocument() {
        return idDocument;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public void setIdDocument(String idDocument) {
        this.idDocument = idDocument;
    }
}
