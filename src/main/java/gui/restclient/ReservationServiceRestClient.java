package gui.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui.ClientApp;
import gui.model.Reservation;
import gui.model.User;
import gui.model.Vehicle;
import gui.restclient.dto.*;
import okhttp3.*;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationServiceRestClient {
    public static final String URL = "http://localhost:8082/api";
    public static final String reservationURL = "/reservation";
    public static final String companyVehicleURL = "/companyVehicle";
    public static final String companyURL = "/company";
    public static final String vehicleURL = "/vehicle";
    public static final String vehicleTypeURL = "/vehicleType";

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public List<Reservation> getAllReservationsFromUser() throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Request request = new Request.Builder()
                .url(URL + reservationURL + "/byUser/" + getUser(ClientApp.getInstance().getToken()).getId())
                .get()
                .addHeader("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response);

        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();

            return objectMapper.readValue(json, ReservationListDto.class).getContent().stream()
                    .map(this::reservationDtoToReservation)
                    .collect(Collectors.toList());
        }

        throw new RuntimeException();
    }

    public List<Reservation> getActiveReservationsFromUser() throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Request request = new Request.Builder()
                .url(URL + reservationURL + "/byUser/" + getUser(ClientApp.getInstance().getToken()).getId())
                .get()
                .addHeader("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response);

        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();

            return objectMapper.readValue(json, ReservationListDto.class).getContent().stream()
                    .filter(ReservationDto::isActive)
                    .map(this::reservationDtoToReservation)
                    .collect(Collectors.toList());
        }

        throw new RuntimeException();
    }

    public List<ReservationDto> getReservations(Long companyVehicleId) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Request request = new Request.Builder()
                .url(URL + reservationURL + "/byCompanyVehicle/" + companyVehicleId)
                .get()
                .addHeader("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response);

        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, ReservationListDto.class).getContent();
        }

        throw new RuntimeException();
    }

    private CompanyVehicleDto getCompanyVehicle(Long companyVehicleId) throws IOException {
        Request request = new Request.Builder()
                .url(URL + companyVehicleURL + "/" +  companyVehicleId)
                .get()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response);

        if(response.code() >= 200 && response.code() <= 300){
            String json = response.body().string();
            return objectMapper.readValue(json, CompanyVehicleDto.class);
        }
        throw new RuntimeException();
    }

    public ReservationDto saveReservation(CreateReservationDto createReservationDto) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(createReservationDto));
        Request request = new Request.Builder()
                .url(URL + reservationURL)
                .post(body)
                .addHeader("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response);

        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, ReservationDto.class);
        }

        throw new RuntimeException();
    }

    public ReservationDto editReservationDto(EditReservationDto editReservationDto) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(editReservationDto));
        Request request = new Request.Builder()
                .url(URL + reservationURL + "/" + editReservationDto.getId())
                .put(body)
                .addHeader("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response);

        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, ReservationDto.class);
        }

        throw new RuntimeException();
    }

    public ReservationDto cancelReservation(ReservationDto reservationDto) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Request request = new Request.Builder()
                .url(URL + reservationURL + "/cancel/" + reservationDto.getId())
                .get()
                .addHeader("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response);

        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, ReservationDto.class);
        }
        throw new RuntimeException();
    }



    public List<Vehicle> getAllVehicles() throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Request request = new Request.Builder()
                .url(URL + companyVehicleURL)
                .get()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response);

        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, CompanyVehicleListDto.class).getContent().stream()
                    .map(this::companyVehicleToVehicle)
                    .collect(Collectors.toList());
        }

        throw new RuntimeException();
    }

    public CompanyDto getCompany(Long companyId) throws IOException {
        Request request = new Request.Builder()
                .url(URL + companyURL + "/" + companyId)
                .get()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response);

        if(response.code() >= 200 && response.code() <= 300){
            String json = response.body().string();
            return objectMapper.readValue(json, CompanyDto.class);
        }
        throw new RuntimeException();
    }

    public VehicleDto getVehicle(Long vehicleId) throws IOException {
        Request request = new Request.Builder()
                .url(URL + vehicleURL + "/" + vehicleId)
                .get()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response);

        if(response.code() >= 200 && response.code() <= 300){
            String json = response.body().string();
            return objectMapper.readValue(json, VehicleDto.class);
        }
        throw new RuntimeException();
    }

    public VehicleTypeDto getVehicleType(Long vehicleTypeId) throws IOException {
        Request request = new Request.Builder()
                .url(URL + vehicleTypeURL + "/" + vehicleTypeId)
                .get()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response);

        if(response.code() >= 200 && response.code() <= 300){
            String json = response.body().string();
            return objectMapper.readValue(json, VehicleTypeDto.class);
        }
        throw new RuntimeException();
    }

    public List<CompanyVehicleDto> filterVehicles(FilterDto filterDto) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(filterDto));
        Request request = new Request.Builder()
                .url(URL + companyVehicleURL + "/filter")
                .put(body)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response);

        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            CompanyVehicleListDto companyVehicles = objectMapper.readValue(json, CompanyVehicleListDto.class);
            return companyVehicles.getContent();
        }

        throw new RuntimeException();
    }

    public Reservation reservationDtoToReservation(ReservationDto reservationDto)  {
        try {
            CompanyVehicleDto companyVehicleDto = getCompanyVehicle(reservationDto.getCompanyVehicleId());
            Vehicle vehicle = companyVehicleToVehicle(companyVehicleDto);
            Reservation reservation = new Reservation(vehicle.getCompany(), vehicle.getManufacturer(), vehicle.getModel(), vehicle.getType(), reservationDto.getStartDate(), reservationDto.getEndDate(), reservationDto.getPriceWithDiscount(), reservationDto);
            return reservation;
        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Error converting ReservationDto to Reservation", e);
        }
    }

    public Vehicle companyVehicleToVehicle(CompanyVehicleDto companyVehicleDto){
        try {
            String company = getCompany(companyVehicleDto.getCompanyId()).getName();
            VehicleDto vehicleDto = getVehicle(companyVehicleDto.getVehicleId());
            String type = getVehicleType(vehicleDto.getVehicleTypeId()).getName();
            Vehicle vehicle = new Vehicle(vehicleDto.getManufacturer(), vehicleDto.getModel(), type, companyVehicleDto.getPrice(), company, companyVehicleDto);
            return vehicle;
        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Error converting ReservationDto to Reservation", e);
        }
    }

    public User getUser(String token) throws JsonProcessingException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(token.split("[.]")[1]));
        User userMapper = objectMapper.readValue(payload, User.class);
        return userMapper;
    }

    public CompanyDto addCompany(CompanyManagerDto companyManagerDto) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(companyManagerDto));
        Request request = new Request.Builder()
                .url(URL + companyURL)
                .post(body)
                .addHeader("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response);

        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, CompanyDto.class);
        }

        throw new RuntimeException();
    }

    public List<CompanyDto> getAllCompanies() throws IOException {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            Request request = new Request.Builder()
                    .url(URL + companyURL)
                    .get()
                    .build();
            Call call = client.newCall(request);
            Response response = call.execute();
            System.out.println(response);

            if (response.code() >= 200 && response.code() <= 300) {
                String json = response.body().string();
                return objectMapper.readValue(json, CompanyListDto.class).getContent();
            }

            throw new RuntimeException();
    }

    public CompanyDto approveCompany(Long companyId, CompanyManagerDto companyManagerDto) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(companyManagerDto));
        Request request = new Request.Builder()
                .url(URL + companyURL + "/approve/" + companyId)
                .put(body)
                .addHeader("Authorization", "Bearer " + ClientApp.getInstance().getToken())
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response);

        if (response.code() >= 200 && response.code() <= 300) {
            String json = response.body().string();
            return objectMapper.readValue(json, CompanyDto.class);
        }

        throw new RuntimeException();
    }

}
