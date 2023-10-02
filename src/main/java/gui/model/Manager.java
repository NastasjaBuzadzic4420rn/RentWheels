package gui.model;

public class Manager {
    private Long id;
    private String username;
    private String email;
    private Long birthDate;
    private String firstName;
    private String lastName;
    private Long startedWorking;
    private boolean active;
    private boolean confirmed;

    public Manager() {
    }

    public Manager(Long id, String username, String email, Long birthDate, String firstName, String lastName, Long startedWorking, boolean active, boolean confirmed) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.startedWorking = startedWorking;
        this.active = active;
        this.confirmed = confirmed;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
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

    public Long getStartedWorking() {
        return startedWorking;
    }

    public void setStartedWorking(Long startedWorking) {
        this.startedWorking = startedWorking;
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
}
