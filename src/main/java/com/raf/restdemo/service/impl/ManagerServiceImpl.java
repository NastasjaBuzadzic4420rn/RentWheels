package com.raf.restdemo.service.impl;

import com.raf.restdemo.comunication.CreateNotification;
import com.raf.restdemo.comunication.NotificationDto;
import com.raf.restdemo.domain.Client;
import com.raf.restdemo.domain.Manager;
import com.raf.restdemo.dto.*;
import com.raf.restdemo.epoch.EpochConverter;
import com.raf.restdemo.exception.NotFoundException;
import com.raf.restdemo.listener.helper.MessageHelper;
import com.raf.restdemo.mapper.ManagerMapper;
import com.raf.restdemo.repository.ManagerRepository;
import com.raf.restdemo.security.activationCode.ActivationCodeGenerator;
import com.raf.restdemo.security.service.TokenService;
import com.raf.restdemo.service.ManagerService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

    TokenService tokenService;
    ManagerMapper managerMapper;
    ManagerRepository managerRepository;

    private JmsTemplate jmsTemplate;
    private MessageHelper messageHelper;
    private String notification;
    private CreateNotification createNotification;
    private EpochConverter epochConverter;

    public ManagerServiceImpl(TokenService tokenService, ManagerMapper managerMapper, ManagerRepository managerRepository,
                              JmsTemplate jmsTemplate, MessageHelper messageHelper,@Value("notification_destination") String notification) {
        this.tokenService = tokenService;
        this.managerMapper = managerMapper;
        this.managerRepository = managerRepository;
        this.jmsTemplate = jmsTemplate;
        this.messageHelper = messageHelper;
        this.notification = notification;
        this.createNotification = new CreateNotification();
        this.epochConverter = new EpochConverter();
    }

    @Override
    public ManagerDto findById(Long id) {
        Manager manager = managerRepository.findById(id).get();
        return managerMapper.managerToManagerDto(manager);
    }

    @Override
    public ManagerDto findByUsername(String username) {
        Manager manager = managerRepository.findByUsername(username).get();
        return managerMapper.managerToManagerDto(manager);
    }

    @Override
    public ManagerDto add(ManagerCreateDto managerCreateDto) {
        //add user
        Manager manager = managerMapper.managerCreateDtoToManager(managerCreateDto);
        manager.setActive(true);
        manager.setConfirmed(false);
        manager.setStartedWorking(epochConverter.toLong(LocalDate.now()));
        ActivationCodeGenerator activationCodeGenerator = new ActivationCodeGenerator();
        String activationCode = activationCodeGenerator.generateCode();
        manager.setActivationCode(activationCode);
        manager = managerRepository.save(manager);

        //send notification
        NotificationDto notification = createNotification.confirmManagerNotification(managerCreateDto, activationCode);
        jmsTemplate.convertAndSend(this.notification, messageHelper.createTextMessage(notification));


        return managerMapper.managerToManagerDto(manager);
    }

    @Override
    public ManagerDto changeActivity(Long id, boolean activity)  {
        Manager manager = managerRepository.findById(id).orElseThrow(() -> new NotFoundException(String
                .format("User with id: %d not found.", id)));
        manager.setActive(activity);
        managerRepository.save(manager);
        return managerMapper.managerToManagerDto(manager);
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        Manager manager = managerRepository.findUserByEmailAndPassword(tokenRequestDto.getEmail(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with email: %s and password: %s not found.", tokenRequestDto.getEmail(),
                                tokenRequestDto.getPassword())));

        if(manager.isActive() && manager.isConfirmed()){
            Claims claims = Jwts.claims();
            claims.put("id", manager.getId());
            claims.put("role", "manager");
            if(manager.isConfirmed())
                claims.put("confirm", true);
            else
                claims.put("confirm", false);
            return new TokenResponseDto(tokenService.generate(claims));
        } else {
            return new TokenResponseDto(null);
        }
    }

    @Override
    public Page<ManagerDto> findAll(Pageable pageable) {
        return managerRepository.findAll(pageable)
                .map(managerMapper::managerToManagerDto);
    }

    @Override
    public ManagerDto edit(Long id, ManagerCreateDto managerCreateDto) {
        //edit manager
        Manager manager = managerRepository.findById(id).orElseThrow(() -> new NotFoundException(String
                .format("User with email: %s and password: %s not found.", managerCreateDto.getEmail(),
                        managerCreateDto.getPassword())));
        manager.setUsername(managerCreateDto.getUsername());
        manager.setPassword(managerCreateDto.getPassword());
        manager.setEmail(managerCreateDto.getEmail());
        manager.setFirstName((managerCreateDto.getFirstName()));
        manager.setLastName(managerCreateDto.getLastName());
        managerRepository.save(manager);


        //send notification
        NotificationDto notification = new NotificationDto();
        notification.setReceiver(managerCreateDto.getEmail());
        notification.setType("Profile_change");
        String param = id + ", " + managerCreateDto.getUsername() + ", " + managerCreateDto.getPassword() + ", " +
                managerCreateDto.getEmail() + ", " + managerCreateDto.getFirstName() + ", " +
                managerCreateDto.getLastName()  + ", " + managerCreateDto.getBirthDate();
        notification.setParameters(param);
        jmsTemplate.convertAndSend(this.notification, messageHelper.createTextMessage(notification));
        return managerMapper.managerToManagerDto(manager);
    }

    @Override
    public ManagerDto confirm(ActivationDto activationDto) {
        Manager manager = managerRepository.findById(activationDto.getUserId()).orElseThrow(() -> new NotFoundException(String
                .format("User with id: %d not found.", activationDto.getUserId())));
        if(manager.getActivationCode().equals(activationDto.getActivationCode())){
            manager.setConfirmed(true);
            managerRepository.save(manager);
        }

        return managerMapper.managerToManagerDto(manager);
    }

}
