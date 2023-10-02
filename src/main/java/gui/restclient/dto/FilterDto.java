package gui.restclient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class FilterDto {
    private String city;
    private String company;
    @JsonProperty("start_date")
    private Long startDate;
    @JsonProperty("end_date")
    private Long endDate;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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
}
