package model;

public class User {
    private String idUser;
    private String name;
    private String phoneNumber;
    private String email;
    private String avatar;
    private String status;
    private String role;

    // Constructor
    public User(String idUser, String name, String phoneNumber, String email, String avatar,
            String status, String role) {
        this.idUser = idUser;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.avatar = avatar;
        this.status = status;
        this.role = role;
    }

    // Getters and setters for all properties

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "idUser='" + idUser + '\'' + ", name='" + name + '\'' + ", phoneNumber='"
                + phoneNumber + '\'' + ", email='" + email + '\'' + ", avatar='" + avatar + '\''
                + ", status='" + status + '\'' + ", role='" + role + '\'' + '}';
    }
}
