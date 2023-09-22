package com.example.reservationservice.repository;

import com.example.reservationservice.domain.CompanyVehicle;
import com.example.reservationservice.dto.CompanyVehicleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyVehicleRepository extends JpaRepository<CompanyVehicle, Long> {


    Optional<Page<CompanyVehicle>> findAllByCompanyId(Long companyId, Pageable pageable);

}
