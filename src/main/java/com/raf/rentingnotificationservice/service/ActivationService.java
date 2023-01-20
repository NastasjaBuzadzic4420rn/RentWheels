package com.raf.rentingnotificationservice.service;

import com.raf.rentingnotificationservice.dto.ActivationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivationService {

    void add(ActivationDto activationDto);

    Page<ActivationDto> findAll(Pageable pageable);

}
