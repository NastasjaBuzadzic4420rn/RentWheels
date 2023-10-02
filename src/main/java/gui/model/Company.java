package gui.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Company {

    private Long id;
    private String name;
    private String description;
    private int numOfVehicles;
    private String city;
    private boolean approved;
    private Manager manager;

    public Company() {
    }

    public Company(Long id, String name, String description, int numOfVehicles, String city, boolean approved, Manager manager) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.numOfVehicles = numOfVehicles;
        this.city = city;
        this.approved = approved;
        this.manager = manager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumOfVehicles() {
        return numOfVehicles;
    }

    public void setNumOfVehicles(int numOfVehicles) {
        this.numOfVehicles = numOfVehicles;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
