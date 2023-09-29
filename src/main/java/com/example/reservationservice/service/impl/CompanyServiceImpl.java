package com.example.reservationservice.service.impl;

import com.example.reservationservice.comunication.CreateNotification;
import com.example.reservationservice.comunication.dto.NotificationDto;
import com.example.reservationservice.domain.Company;
import com.example.reservationservice.dto.CompanyCreateDto;
import com.example.reservationservice.dto.CompanyDto;
import com.example.reservationservice.dto.CompanyManagerDto;
import com.example.reservationservice.exception.NotFoundException;
import com.example.reservationservice.listener.helper.MessageHelper;
import com.example.reservationservice.mapper.CompanyMapper;
import com.example.reservationservice.repository.CompanyRepository;
import com.example.reservationservice.service.CompanyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository repository;
    private CompanyMapper mapper;

    private JmsTemplate jmsTemplate;
    private MessageHelper messageHelper;
    private String notification;
    private CreateNotification createNotification;


    public CompanyServiceImpl(CompanyRepository repository, CompanyMapper mapper, JmsTemplate jmsTemplate, MessageHelper messageHelper,@Value("notification_destination")  String notification) {
        this.repository = repository;
        this.mapper = mapper;
        this.jmsTemplate = jmsTemplate;
        this.messageHelper = messageHelper;
        this.notification = notification;
        this.createNotification = new CreateNotification();
    }

    @Override
    public Page<CompanyDto> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::objectToDto);
    }

    @Override
    public Page<CompanyDto> findAllByCityAndName(String city, String name, Pageable pageable) {
        return repository.findAllByCityIgnoreCaseAndNameIsContainingIgnoreCase(city, name, pageable).map(companyPage -> companyPage.map(mapper::objectToDto))
                .orElseThrow(() -> new NotFoundException(String
                        .format("Company with city: %s and name containing: %s not found.", city, name)));
    }

    @Override
    public Page<CompanyDto> findAllByName(String name, Pageable pageable) {
        return repository.findAllByNameIsContainingIgnoreCase(name, pageable).map(companyPage -> companyPage.map(mapper::objectToDto))
                .orElseThrow(() -> new NotFoundException(String
                        .format("Company with city: %s and name containing: %s not found.", name)));
    }

    @Override
    public CompanyDto findById(Long id) {
        Company company = repository.findById(id).get();
        return mapper.objectToDto(company);
    }


    @Override
    public CompanyDto add(CompanyManagerDto companyManagerDto) {
        Company company = mapper.compManDtoToObject(companyManagerDto);
        company.setNumOfVehicles(0);
        company.setApproved(false);
        repository.save(company);

        NotificationDto notification = createNotification.waitForApproveNotification(companyManagerDto);
        jmsTemplate.convertAndSend(this.notification, messageHelper.createTextMessage(notification));
        return mapper.objectToDto(company);
    }

    @Override
    public CompanyDto edit(Long id, CompanyCreateDto companyCreateDto) {
        Company company = repository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Company with id: %s not found.", id)));
        company.setName(companyCreateDto.getName());
        company.setDescription(companyCreateDto.getDescription());
        company.setCity(companyCreateDto.getCity());
        repository.save(company);
        return mapper.objectToDto(company);
    }

    @Override
    public CompanyDto approve(Long id) {
        Company company = repository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Company with id: %s not found.", id)));
        company.setApproved(true);
        repository.save(company);
        return mapper.objectToDto(company);
    }
}
