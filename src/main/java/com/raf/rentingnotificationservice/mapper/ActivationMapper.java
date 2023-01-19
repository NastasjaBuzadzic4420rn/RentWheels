package com.raf.rentingnotificationservice.mapper;

import com.raf.rentingnotificationservice.domain.Activation;
import com.raf.rentingnotificationservice.dto.ActivationDto;
import org.springframework.stereotype.Component;

@Component
public class ActivationMapper {

    public ActivationDto activationToActivationDto(Activation activation){
        ActivationDto activationDto = new ActivationDto();
        activationDto.setId(activation.getId());
        activationDto.setEmail(activationDto.getEmail());
        activationDto.setFirstName(activation.getFirstName());
        activationDto.setLastName(activation.getLastName());
        activationDto.setPassword(activationDto.getPassword());
        activationDto.setUsername(activation.getUsername());
        activationDto.setActivationKey(activation.getActivationKey());
        return activationDto;
    }
}
