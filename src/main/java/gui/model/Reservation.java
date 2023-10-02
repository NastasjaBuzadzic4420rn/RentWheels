package gui.model;

import gui.restclient.dto.ReservationDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Reservation {
    private String company;
    private String manufacturer;
    private String model;
    private String type;
    private Long startDate;
    private Long endDate;
    private double priceWithDiscount;
    private ReservationDto reservationDto;

    public Reservation() {
    }

    public Reservation(String company, String manufacturer, String model, String type, Long startDate, Long endDate, double priceWithDiscount, ReservationDto reservationDto) {
        this.company = company;
        this.manufacturer = manufacturer;
        this.model = model;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceWithDiscount = priceWithDiscount;
        this.reservationDto = reservationDto;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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


    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(double priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    public ReservationDto getReservationDto() {
        return reservationDto;
    }

    public void setReservationDto(ReservationDto reservationDto) {
        this.reservationDto = reservationDto;
    }
}
