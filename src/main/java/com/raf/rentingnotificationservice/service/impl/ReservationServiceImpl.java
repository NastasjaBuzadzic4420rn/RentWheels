package com.raf.rentingnotificationservice.service.impl;

import com.raf.rentingnotificationservice.domain.Activation;
import com.raf.rentingnotificationservice.domain.Reservation;
import com.raf.rentingnotificationservice.dto.ReservationDto;
import com.raf.rentingnotificationservice.mapper.ReservationMapper;
import com.raf.rentingnotificationservice.repository.ReservationRepository;
import com.raf.rentingnotificationservice.service.ReservationService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService{

    private ReservationService reservationService;
    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;

    public ReservationServiceImpl(@Lazy ReservationService reservationService, @Lazy ReservationRepository reservationRepository,@Lazy ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public void add(ReservationDto reservationDto) {
        Reservation reservation = reservationMapper.reservationDtoToReservation(reservationDto);
        System.out.println("Rezervacij dodata");
        reservationRepository.save(reservation);
    }

    @Override
    public Page<ReservationDto> findAll(Pageable pageable) {
        return null;
    }
}
