package com.raf.rentingnotificationservice.service;

import com.raf.rentingnotificationservice.dto.ActivationCreateDto;
import com.raf.rentingnotificationservice.dto.ActivationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivationService {

    ActivationDto add(ActivationCreateDto activationCreateDto);

    Page<ActivationDto> findAll(Pageable pageable);

    ActivationDto edit(Long id, ActivationCreateDto activationCreateDto);
}
