package com.example.reservationservice.controller;

import com.example.reservationservice.dto.CompanyCreateDto;
import com.example.reservationservice.dto.CompanyDto;
import com.example.reservationservice.dto.CompanyManagerDto;
import com.example.reservationservice.dto.FilterDto;
import com.example.reservationservice.security.CheckSecurity;
import com.example.reservationservice.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<Page<CompanyDto>> getAllCompanies(Pageable pageable) {
        return new ResponseEntity<>(companyService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(companyService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles = {"admin", "manager"})
    public ResponseEntity<CompanyDto> saveCompany(@RequestHeader("Authorization") String authorization, @RequestBody @Valid CompanyManagerDto companyManagerDto) {
        return new ResponseEntity<>(companyService.add(companyManagerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> editCompany(@PathVariable("id") Long id, @RequestBody CompanyCreateDto companyCreateDto){
        return new ResponseEntity<>(companyService.edit(id, companyCreateDto), HttpStatus.ACCEPTED);
    }

    @PutMapping("/approve/{id}")
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<CompanyDto> approveCompany(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        return new ResponseEntity<>(companyService.approve(id), HttpStatus.ACCEPTED);
    }

}
