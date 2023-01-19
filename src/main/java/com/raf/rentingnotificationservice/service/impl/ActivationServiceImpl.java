package com.raf.rentingnotificationservice.service.impl;

import com.raf.rentingnotificationservice.dto.ActivationCreateDto;
import com.raf.rentingnotificationservice.dto.ActivationDto;
import com.raf.rentingnotificationservice.mapper.ActivationMapper;
import com.raf.rentingnotificationservice.repository.ActivationRepository;
import com.raf.rentingnotificationservice.service.ActivationService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActivationServiceImpl implements ActivationService {


    private ActivationService activationService;
    private ActivationMapper activationMapper;
    private ActivationRepository activationRepository;

    public ActivationServiceImpl(@Lazy ActivationService activationService,@Lazy  ActivationMapper activationMapper,@Lazy  ActivationRepository activationRepository) {
        this.activationService = activationService;
        this.activationMapper = activationMapper;
        this.activationRepository = activationRepository;
    }


    @Override
    public ActivationDto add(ActivationCreateDto activationCreateDto) {
        return null;
    }

    @Override
    public Page<ActivationDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ActivationDto edit(Long id, ActivationCreateDto activationCreateDto) {
        return null;
    }
}
