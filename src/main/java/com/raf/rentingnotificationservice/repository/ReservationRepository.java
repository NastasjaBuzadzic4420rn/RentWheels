package com.raf.rentingnotificationservice.repository;

import com.raf.rentingnotificationservice.domain.Activation;
import com.raf.rentingnotificationservice.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository  extends JpaRepository<Reservation, Long> {
}
