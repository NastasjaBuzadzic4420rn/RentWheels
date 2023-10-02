package com.raf.restdemo.dto;

public class DiscountDto {

    private double discount;

    private String rank;

    public DiscountDto() {

    }

    public DiscountDto(double discount, String rank) {
        this.discount = discount;
        this.rank = rank;
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
