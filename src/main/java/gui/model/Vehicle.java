package gui.model;

import gui.restclient.dto.CompanyVehicleDto;

public class Vehicle {

    private String manufacturer;
    private String model;
    private String type;
    private double pricePerDay;
    private String company;
    private CompanyVehicleDto companyVehicleDto;

    public Vehicle(String manufacturer, String model, String type, double pricePerDay, String company, CompanyVehicleDto companyVehicleDto) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.type = type;
        this.pricePerDay = pricePerDay;
        this.company = company;
        this.companyVehicleDto = companyVehicleDto;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public CompanyVehicleDto getCompanyVehicleDto() {
        return companyVehicleDto;
    }

    public void setCompanyVehicleDto(CompanyVehicleDto companyVehicleDto) {
        this.companyVehicleDto = companyVehicleDto;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

}
