package com.example.reservationservice.service.impl;

import com.example.reservationservice.domain.Reservation;
import com.example.reservationservice.domain.UnavailablePeriod;
import com.example.reservationservice.dto.CreateReservationDto;
import com.example.reservationservice.dto.EditReservationDto;
import com.example.reservationservice.dto.ReservationDto;
import com.example.reservationservice.exception.NotFoundException;
import com.example.reservationservice.mapper.ReservationMapper;
import com.example.reservationservice.mapper.UnavailablePeriodsMapper;
import com.example.reservationservice.repository.ReservationRepository;
import com.example.reservationservice.repository.UnavailablePeriodsRepository;
import com.example.reservationservice.service.ReservationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository repository;
    private UnavailablePeriodsRepository unavailablePeriodsRepository;
    private ReservationMapper mapper;

    public ReservationServiceImpl(ReservationRepository repository, UnavailablePeriodsRepository unavailablePeriodsRepository, ReservationMapper mapper) {
        this.repository = repository;
        this.unavailablePeriodsRepository = unavailablePeriodsRepository;
        this.mapper = mapper;
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
    public ReservationDto findById(Long id) {
        Reservation reservation = repository.findById(id).get();
        return mapper.objectToDto(reservation);
    }

    @Override
    public ReservationDto add(CreateReservationDto createReservationDto) {
        Reservation reservation = mapper.createReservationDtoToObject(createReservationDto);
        repository.save(reservation);

        UnavailablePeriod period = new UnavailablePeriod();
        period.setCompanyVehicleId(createReservationDto.getCompanyVehicleId());
        period.setStartDate(createReservationDto.getStartDate());
        period.setEndDate(createReservationDto.getEndDate());
        unavailablePeriodsRepository.save(period);

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
        Reservation reservation = repository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Reservation with id: %s not found.", id)));
        reservation.setActive(false);
        repository.save(reservation);

        UnavailablePeriod period = unavailablePeriodsRepository.findByCompanyVehicleIdAndAndStartDateAndAndEndDate(reservation.getCompanyVehicleId(), reservation.getStartDate(), reservation.getEndDate()).get();
        unavailablePeriodsRepository.delete(period);

        return mapper.objectToDto(reservation);
    }
}
