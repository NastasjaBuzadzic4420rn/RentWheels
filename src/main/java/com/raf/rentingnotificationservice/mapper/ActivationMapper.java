package com.raf.rentingnotificationservice.mapper;

import com.raf.rentingnotificationservice.domain.Activation;
import com.raf.rentingnotificationservice.dto.ActivationDto;
import org.springframework.stereotype.Component;

@Component
public class ActivationMapper {

    public Activation activationDtoToActivation(ActivationDto activationDto){
        Activation activation = new Activation();
        activation.setEmail(activationDto.getEmail());
        activation.setFirstName(activationDto.getFirstName());
        activation.setLastName(activationDto.getLastName());
        activation.setPassword(activationDto.getPassword());
        activation.setUsername(activationDto.getUsername());
        activation.setActivationKey(activationDto.getActivationKey());
        activation.setRole(activation.getRole());
        return activation;
    }


}
