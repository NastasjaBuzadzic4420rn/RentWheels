package gui.restclient.dto;

public class ClientDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private int sumOfRentalDays;
    private boolean active;
    private boolean confirmed;
    private String activationCode;
    private int points;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSumOfRentalDays() {
        return sumOfRentalDays;
    }

    public void setSumOfRentalDays(int sumOfRentalDays) {
        this.sumOfRentalDays = sumOfRentalDays;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sumOfRentalDays=" + sumOfRentalDays +
                ", active=" + active +
                ", confirmed=" + confirmed +
                ", activationCode='" + activationCode + '\'' +
                ", points=" + points +
                '}';
    }
}
