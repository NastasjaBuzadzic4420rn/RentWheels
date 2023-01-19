package com.raf.rentingnotificationservice.repository;

import com.raf.rentingnotificationservice.domain.Activation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivationRepository  extends JpaRepository<Activation, Long> {
}
