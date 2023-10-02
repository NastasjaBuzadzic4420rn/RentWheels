package gui.restclient.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;


public class CompanyVehicleDto {
    private Long id;
    @JsonProperty("company_id")
    private Long companyId;
    @JsonProperty("vehicle_id")
    private Long vehicleId;
    private double price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
