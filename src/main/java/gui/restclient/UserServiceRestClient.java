package gui.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui.ClientApp;
import gui.model.User;
import gui.restclient.dto.*;
import okhttp3.*;

import java.io.IOException;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;

public class UserServiceRestClient {
    public static final String URL = "http://localhost:8080/api";
    public static final String clientURL = "/client";
    public static final String managerURL = "/manager";
    public static final String statusURL = "/status";

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public String login(String email, String password) throws IOException {
        TokenRequestDto tokenRequestDto = new TokenRequestDto(email, password);
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(tokenRequestDto));

        Request request = new Request.Builder()
                .url(URL + "/login")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response);
        if (response.code() == 200) {
            String json = response.body().string();
            TokenResponseDto dto = objectMapper.readValue(json, TokenResponseDto.class);

            return dto.getToken();
        } else{
            throw new RuntimeException("Invalid username or password");
        }
    }

    public ClientDto registerClient(ClientCreateDto clientCreateDto) throws IOException {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(clientCreateDto));

        Request request = new Request.Builder()
                .url(URL + clientURL)
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response);
        if (response.code() == 201) {
            String json = response.body().string();
            return objectMapper.readValue(json, ClientDto.class);

        } else {
            throw new RuntimeException("Something went wrong");
        }
    }

    public ManagerDto registerManager(ManagerCreateDto managerCreateDto) throws IOException {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(managerCreateDto));
        Request request = new Request.Builder()
                .url(URL + managerURL)
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response);
        if (response.code() == 201) {
            String json = response.body().string();
            return objectMapper.readValue(json, ManagerDto.class);

        } else {
            throw new RuntimeException("Something went wrong");
        }
    }

    public User getUser(String token) throws JsonProcessingException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        if (token == null)
            return null;
        String payload = new String(decoder.decode(token.split("[.]")[1]));
        User user = objectMapper.readValue(payload, User.class);
        return user;
    }

    public ClientDto editClient(ClientCreateDto clientCreateDto) throws IOException {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(clientCreateDto));

        Request request = new Request.Builder()
                .url(URL + clientURL + "/edit/" + getUser(ClientApp.getInstance().getToken()).getId())
                .put(body)
                .addHeader("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .build();
        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response);
        if (response.code() == 201 || response.code() == 200  || response.code() == 202) {
            String json = response.body().string();
            return objectMapper.readValue(json, ClientDto.class);
        } else {
            throw new RuntimeException("Something went wrong");
        }
    }

    public ClientDto confirmClient(ActivationDto activationDto) throws IOException {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(activationDto));

        Request request = new Request.Builder()
                .url(URL + clientURL + "/confirmRegistration")
                .put(body)
                .build();
        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response);
        if (response.code() == 201 || response.code() == 200  || response.code() == 202) {
            String json = response.body().string();
            return objectMapper.readValue(json, ClientDto.class);
        } else {
            throw new RuntimeException("Something went wrong");
        }
    }

    public ManagerDto confirmManager(ActivationDto activationDto) throws IOException {
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(activationDto));

        Request request = new Request.Builder()
                .url(URL + managerURL + "/confirmRegistration")
                .put(body)
                .build();
        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response);
        if (response.code() == 201 || response.code() == 200  || response.code() == 202) {
            String json = response.body().string();
            return objectMapper.readValue(json, ManagerDto.class);
        } else {
            throw new RuntimeException("Something went wrong");
        }
    }

    public ManagerDto getManager(Long managerId) throws IOException {
        Request request = new Request.Builder()
                .url(URL + managerURL + "/" + managerId)
                .get()
                .addHeader("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .build();
        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response);
        if (response.code() == 201 || response.code() == 200  || response.code() == 202) {
            String json = response.body().string();
            return objectMapper.readValue(json, ManagerDto.class);
        } else {
            throw new RuntimeException("Something went wrong");
        }
    }

    public ManagerDto getManagerByUsername(String username) throws IOException {
        Request request = new Request.Builder()
                .url(URL + managerURL + "/byUsername/" + username)
                .get()
                .addHeader("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .build();
        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response);
        if (response.code() == 201 || response.code() == 200  || response.code() == 202) {
            String json = response.body().string();
            return objectMapper.readValue(json, ManagerDto.class);
        } else {
            throw new RuntimeException("Something went wrong");
        }
    }

    public UserStatusDto getUserStatus() throws IOException {
        Request request = new Request.Builder()
                .url(URL + statusURL + "/getStatusFor/" + getUser(ClientApp.getInstance().getToken()).getId())
                .get()
                .build();
        Call call = client.newCall(request);

        Response response = call.execute();
        System.out.println(response);
        if (response.code() == 201 || response.code() == 200  || response.code() == 202) {
            String json = response.body().string();
            List<UserStatusDto> list = objectMapper.readValue(json, UserStatusListDto.class).getContent();
            return list.stream().max(Comparator.comparing(UserStatusDto::getDiscount)).orElse(null);
        } else {
            throw new RuntimeException("Something went wrong");
        }
    }




}
