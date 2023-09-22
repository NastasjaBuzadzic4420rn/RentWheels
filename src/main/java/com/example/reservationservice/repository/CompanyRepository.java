package com.example.reservationservice.repository;

import com.example.reservationservice.domain.Company;
import com.example.reservationservice.dto.CompanyDto;
import com.example.reservationservice.dto.FilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Page<Company>> findAllByCityIgnoreCaseAndNameIsContainingIgnoreCase(String city, String name, Pageable pageable);

    Optional<Page<Company>> findAllByNameIsContainingIgnoreCase(String name, Pageable pageable);

}
