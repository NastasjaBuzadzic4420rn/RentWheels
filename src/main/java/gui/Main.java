package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import gui.view.LoginView;


public class Main extends Application {

    public static Stage mainStage;
    public static Stage detailVehicleStage;
    public static Stage activationStage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        mainStage = new Stage();
        detailVehicleStage = new Stage();
        activationStage = new Stage();
        Scene sc = new Scene(new LoginView(), 450, 350);
//        Scene sc = new Scene(new DetailVehicleView(new Vehicle("Ford", "Focus", "SUV", 34.0, "Rent a car", null)), 400, 450);
        mainStage.setTitle("RentWheels");
        mainStage.setScene(sc);
        mainStage.show();

    }


}
