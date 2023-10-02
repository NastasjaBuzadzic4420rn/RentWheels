package gui.controller;

import gui.model.Vehicle;
import gui.restclient.UserServiceRestClient;

import java.io.IOException;

public class CalculatePriceController {
    private UserServiceRestClient userServiceRestClient;

    public CalculatePriceController() {
        this.userServiceRestClient = new UserServiceRestClient();
    }

    public double calculatePrice(double price){
        double discount = 1;
        try {
            discount = userServiceRestClient.getUserStatus().getDiscount();
        } catch (IOException e){
            e.printStackTrace();
        }
        return price * discount;
    }
}
