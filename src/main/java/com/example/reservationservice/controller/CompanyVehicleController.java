package com.example.reservationservice.controller;

import com.example.reservationservice.dto.*;
import com.example.reservationservice.service.CompanyService;
import com.example.reservationservice.service.CompanyVehicleService;
import com.example.reservationservice.service.ReservationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/companyVehicle")
public class CompanyVehicleController {

    private CompanyVehicleService companyVehicleService;
    private CompanyService companyService;
    private ReservationService reservationService;

    public CompanyVehicleController(CompanyVehicleService companyVehicleService, CompanyService companyService, ReservationService reservationService) {
        this.companyVehicleService = companyVehicleService;
        this.companyService = companyService;
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<Page<CompanyVehicleDto>> getAllCompanyVehicles(Pageable pageable) {
        return new ResponseEntity<>(companyVehicleService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyVehicleDto> getCompanyVehicleById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(companyVehicleService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/filter")
    public ResponseEntity<Page<CompanyVehicleDto>> filter(@RequestBody FilterDto filterDto, Pageable pageable){

        if(filterDto.getCompany() == null)
            filterDto.setCompany("");

        List<CompanyVehicleDto> filteredVehicles = new ArrayList<>();

        //Filter with city and company
        if(filterDto.getCity() != null && !filterDto.getCity().equals("")) {
            Page<CompanyDto> companies = companyService.findAllByCityAndName(filterDto.getCity(), filterDto.getCompany(), pageable);
            for (CompanyDto company : companies)
                filteredVehicles.addAll(companyVehicleService.findAllByCompanyId(company.getId(), pageable).getContent());

        } else {
            System.out.println(filterDto.getCompany());
            Page<CompanyDto> companies = companyService.findAllByName(filterDto.getCompany(), pageable);
            System.out.println(companies);
            for (CompanyDto company : companies)
                filteredVehicles.addAll(companyVehicleService.findAllByCompanyId(company.getId(), pageable).getContent());

        }

        //Filter with period
        List<CompanyVehicleDto> filteredByDates = new ArrayList<>();
        if(filterDto.getStartDate() != null && filterDto.getEndDate() != null){
            for(CompanyVehicleDto vehicle : filteredVehicles){
                boolean available = true;
                for(ReservationDto reservation : reservationService.findAllByCompanyVehicle(vehicle.getId(), pageable)){
                    if((reservation.getStartDate() > filterDto.getStartDate() && reservation.getStartDate() < filterDto.getEndDate())
                    || (reservation.getEndDate() > filterDto.getStartDate() && reservation.getEndDate() < filterDto.getEndDate())
                    || (reservation.getStartDate() < filterDto.getStartDate() && reservation.getEndDate() > filterDto.getEndDate())){
                        available = false;
                        break;
                    }
                }
                if(available)
                    filteredByDates.add(vehicle);
            }
            filteredVehicles = filteredByDates;
        }

        filteredVehicles.sort(Comparator.comparingDouble(CompanyVehicleDto::getPrice));
        return new ResponseEntity<>(new PageImpl<>(filteredVehicles, pageable, filteredVehicles.size()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CompanyVehicleDto> saveCompanyVehicle(@RequestBody @Valid CompanyVehicleDto companyVehicleDto) {
        return new ResponseEntity<>(companyVehicleService.add(companyVehicleDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyVehicleDto> editCompanyVehicle(@PathVariable("id") Long id, @RequestBody CompanyVehicleDto companyVehicleDto){
        return new ResponseEntity<>(companyVehicleService.edit(id, companyVehicleDto), HttpStatus.ACCEPTED);
    }


    public CompanyVehicleDto findById(Long id){
        return companyVehicleService.findById(id);
    }
}
