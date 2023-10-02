package com.raf.restdemo.service;

import com.raf.restdemo.comunication.PointsDto;
import com.raf.restdemo.dto.*;
import com.raf.restdemo.dto.TokenRequestDto;
import com.raf.restdemo.dto.TokenResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    Page<ClientDto> findAll(Pageable pageable);

    ClientDto findById(Long id);

    ClientDto add(ClientCreateDto clientCreateDto);

    ClientDto changePoints(PointsDto pointsDto);

    ClientDto edit(Long id, ClientCreateDto clientCreateDto);

    ClientDto changeActivity(Long id, boolean activity);

    ClientDto confirm(ActivationDto activationDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

}

