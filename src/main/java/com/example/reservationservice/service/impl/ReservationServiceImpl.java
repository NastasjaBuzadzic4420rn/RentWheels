package com.example.reservationservice.service.impl;

import com.example.reservationservice.comunication.CreateNotification;
import com.example.reservationservice.comunication.dto.PointsDto;
import com.example.reservationservice.domain.*;
import com.example.reservationservice.dto.*;
import com.example.reservationservice.epoch.EpochConverter;
import com.example.reservationservice.exception.NotFoundException;
import com.example.reservationservice.listener.helper.MessageHelper;
import com.example.reservationservice.mapper.ReservationMapper;
import com.example.reservationservice.repository.*;
import com.example.reservationservice.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository repository;
    private ReservationMapper mapper;
    private JmsTemplate jmsTemplate;
    private MessageHelper messageHelper;
    private String notification;

    private CompanyVehicleRepository companyVehicleRepository;
    private VehicleRepository vehicleRepository;
    private VehicleTypeRepository vehicleTypeRepository;
    private CompanyRepository companyRepository;
    private EpochConverter epochConverter;



    public ReservationServiceImpl(ReservationRepository repository, ReservationMapper mapper, JmsTemplate jmsTemplate, MessageHelper messageHelper,@Value("points_destination") String notification, CompanyVehicleRepository companyVehicleRepository, VehicleRepository vehicleRepository, VehicleTypeRepository vehicleTypeRepository, CompanyRepository companyRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.jmsTemplate = jmsTemplate;
        this.messageHelper = messageHelper;
        this.notification = notification;
        this.companyVehicleRepository = companyVehicleRepository;
        this.vehicleRepository = vehicleRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.companyRepository = companyRepository;
        this.epochConverter = new EpochConverter();
    }

    @Override
    public Page<ReservationDto> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::objectToDto);
    }

    @Override
    public Page<ReservationDto> findAllByUser(Long userId, Pageable pageable) {
        Page<Reservation> reservations = repository.findAllByUserId(userId, pageable)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Reservations with userId: %d.", userId)));
        return reservations.map(mapper::objectToDto);
    }

    @Override
    public Page<ReservationDto> findAllByCompanyVehicle(Long companyVehicleId, Pageable pageable) {
        Page<Reservation> reservations = repository.findAllByCompanyVehicleId(companyVehicleId, pageable)
                .orElseThrow(() -> new NotFoundException(String
                        .format("No reservations with companyVehicleId: %d.", companyVehicleId)));
        return reservations.map(mapper::objectToDto);
    }


    @Override
    public ReservationDto findById(Long id) {
        Reservation reservation = repository.findById(id).get();
        return mapper.objectToDto(reservation);
    }

    @Override
    public ReservationDto add(CreateReservationDto createReservationDto) {
        LocalDate startDate = epochConverter.toLocalDate(createReservationDto.getStartDate());
        LocalDate endDate = epochConverter.toLocalDate(createReservationDto.getEndDate());
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        //Save reservation
        Reservation reservation = mapper.createReservationDtoToObject(createReservationDto);
        reservation.setActive(true);
        reservation.setPriceWithDiscount(createReservationDto.getPriceWithDiscount() * days);
        repository.save(reservation);

        //Add points
        CompanyVehicle companyVehicle = companyVehicleRepository.findById(createReservationDto.getCompanyVehicleId()).get();
        Vehicle vehicle = vehicleRepository.findById(companyVehicle.getVehicleId()).get();
        VehicleType vehicleType = vehicleTypeRepository.findById(vehicle.getVehicleTypeId()).get();
        Company company = companyRepository.findById(companyVehicle.getCompanyId()).get();

        PointsDto pointsDto = new PointsDto();
        pointsDto.setUserId(createReservationDto.getUserId());
        pointsDto.setModel(vehicle.getModel());
        pointsDto.setManufacturer(vehicle.getManufacturer());
        pointsDto.setType(vehicleType.getName());
        pointsDto.setStartDate(createReservationDto.getStartDate());
        pointsDto.setEndDate(createReservationDto.getEndDate());
        pointsDto.setCompany(company.getName());
        pointsDto.setCity(company.getCity());
        pointsDto.setPrice(createReservationDto.getPriceWithDiscount());
        pointsDto.setAdd(true);
        jmsTemplate.convertAndSend(this.notification, messageHelper.createTextMessage(pointsDto));

        return mapper.objectToDto(reservation);
    }

    @Override
    public ReservationDto edit(Long id, EditReservationDto editReservationDto) {
        Reservation reservation = repository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Reservation with id: %s not found.", id)));
        reservation.setStartDate(editReservationDto.getStartDate());
        reservation.setEndDate(editReservationDto.getEndDate());
        repository.save(reservation);
        return mapper.objectToDto(reservation);
    }

    @Override
    public ReservationDto cancel(Long id) {
        //Cancel reservation
        Reservation reservation = repository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Reservation with id: %s not found.", id)));
        reservation.setActive(false);
        repository.save(reservation);

        //Subtract points
        CompanyVehicle companyVehicle = companyVehicleRepository.findById(reservation.getCompanyVehicleId()).get();
        Vehicle vehicle = vehicleRepository.findById(companyVehicle.getVehicleId()).get();
        VehicleType vehicleType = vehicleTypeRepository.findById(vehicle.getVehicleTypeId()).get();
        Company company = companyRepository.findById(companyVehicle.getCompanyId()).get();

        PointsDto pointsDto = new PointsDto();
        pointsDto.setUserId(reservation.getUserId());
        pointsDto.setModel(vehicle.getModel());
        pointsDto.setManufacturer(vehicle.getManufacturer());
        pointsDto.setType(vehicleType.getName());
        pointsDto.setStartDate(reservation.getStartDate());
        pointsDto.setEndDate(reservation.getEndDate());
        pointsDto.setCompany(company.getName());
        pointsDto.setCity(company.getCity());
        pointsDto.setPrice(reservation.getPriceWithDiscount());
        pointsDto.setAdd(false);
        jmsTemplate.convertAndSend(this.notification, messageHelper.createTextMessage(pointsDto));


        return mapper.objectToDto(reservation);
    }
}
