package org.example.dto;

public class UserDto {
    private String email;
    private String name;

    private String password;
    private String branches;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public UserDto(int id ,String email, String name, String password, String branches) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.branches = branches;
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", branches='" + branches + '\'' +
                '}';
    }

    public UserDto(String email, String name, String password, String branches) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.branches = branches;
    }

    public UserDto() {
    }

    public UserDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBranch() {
        return branches;
    }

    public void setBranch(String branch) {
        this.branches = branch;
    }

}
