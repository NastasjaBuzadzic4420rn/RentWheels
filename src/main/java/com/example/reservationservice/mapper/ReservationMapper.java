package com.example.reservationservice.mapper;

import com.example.reservationservice.domain.Reservation;
import com.example.reservationservice.dto.CreateReservationDto;
import com.example.reservationservice.dto.ReservationDto;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public Reservation createReservationDtoToObject(CreateReservationDto createReservationDto){
        Reservation reservation = new Reservation();
        reservation.setUserId(createReservationDto.getUserId());
        reservation.setCompanyVehicleId(createReservationDto.getCompanyVehicleId());
        reservation.setPriceWithDiscount(createReservationDto.getPriceWithDiscount());
        reservation.setStartDate(createReservationDto.getStartDate());
        reservation.setEndDate(createReservationDto.getEndDate());
        reservation.setActive(true);
        return reservation;
    }

    public ReservationDto objectToDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setUserId(reservation.getUserId());
        reservationDto.setCompanyVehicleId(reservation.getCompanyVehicleId());
        reservationDto.setPriceWithDiscount(reservation.getPriceWithDiscount());
        reservationDto.setStartDate(reservation.getStartDate());
        reservationDto.setEndDate(reservation.getEndDate());
        reservationDto.setActive(reservation.isActive());
        return reservationDto;
    }
}
