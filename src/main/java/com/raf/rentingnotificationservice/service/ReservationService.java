package com.raf.rentingnotificationservice.service;

import com.raf.rentingnotificationservice.dto.ActivationDto;
import com.raf.rentingnotificationservice.dto.ReservationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservationService {

    void add(ReservationDto reservationDto);

    Page<ReservationDto> findAll(Pageable pageable);
}
