package ecommercemicroservice.auth_service.request;

public class RequestForCreateUser {

    private String email;
    private String password;
    private String firstname;
    private String lastname;

    public RequestForCreateUser() {
    }

    public RequestForCreateUser(String email, String password, String firstname, String lastname) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
