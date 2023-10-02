package gui;

import gui.restclient.dto.ClientCreateDto;
import gui.restclient.dto.ClientDto;

public class ClientApp {

    private String token;

    private ClientApp()throws IllegalAccessException, NoSuchMethodException{}

    private static class InstanceHolder {
        private static ClientApp instance;

        static {
            try {
                instance = new ClientApp();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static ClientApp getInstance() {
        return InstanceHolder.instance;
    }
}
