package gui.controller;

import gui.restclient.UserServiceRestClient;
import gui.restclient.dto.ClientCreateDto;
import gui.restclient.dto.ClientDto;
import gui.view.EditView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;

public class EditController implements EventHandler<ActionEvent> {

    private EditView editView;
    private UserServiceRestClient userServiceRestClient;

    public EditController(EditView editView) {
        this.editView = editView;
        this.userServiceRestClient = new UserServiceRestClient();
    }

    @Override
    public void handle(ActionEvent event) {

        ClientCreateDto clientCreateDto = new ClientCreateDto();
        clientCreateDto.setFirstName(editView.getTfFirstName().getText());
        clientCreateDto.setLastName(editView.getTfLastName().getText());
        clientCreateDto.setEmail(editView.getTfEmail().getText());
        clientCreateDto.setUsername(editView.getTfUsername().getText());
        clientCreateDto.setPassword(editView.getTfPassword().getText());

        try {
            ClientDto client = userServiceRestClient.editClient(clientCreateDto);
            System.out.println(client);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
