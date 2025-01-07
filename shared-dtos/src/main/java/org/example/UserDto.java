package org.example;

public class UserDto {

    private String email;
    private String firstname;
    private String lastname;

    public UserDto() {
    }

    public UserDto(String email, String firstname, String lastname) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
