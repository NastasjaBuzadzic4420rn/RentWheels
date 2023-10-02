package com.raf.restdemo.dto;

public class UserStatusDto {

    private Integer minPoints;
    private Integer maxPoints;
    private double discount;
    private String rank;

    public UserStatusDto() {

    }

    public UserStatusDto(Integer minPoints, Integer maxPoints, double discount, String rank) {
        this.minPoints = minPoints;
        this.maxPoints = maxPoints;
        this.discount = discount;
        this.rank = rank;
    }

    public Integer getMinPoints() {
        return minPoints;
    }

    public void setMinPoints(Integer minPoints) {
        this.minPoints = minPoints;
    }

    public Integer getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(Integer maxPoints) {
        this.maxPoints = maxPoints;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
