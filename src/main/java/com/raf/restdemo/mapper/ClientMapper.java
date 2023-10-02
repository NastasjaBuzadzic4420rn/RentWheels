package com.raf.restdemo.mapper;

import com.raf.restdemo.domain.Client;
import com.raf.restdemo.dto.ClientCreateDto;
import com.raf.restdemo.dto.ClientDto;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {


    public ClientMapper() {

    }

    public ClientDto userToUserDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setEmail(client.getEmail());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setUsername(client.getUsername());
        clientDto.setPassword(client.getPassword());
        clientDto.setActive(client.isActive());
        clientDto.setConfirmed(client.isConfirmed());
        clientDto.setActivationCode(client.getActivationCode());
        clientDto.setPoints(client.getPoints());
        return clientDto;
    }

    public Client userCreateDtoToUser(ClientCreateDto clientCreateDto) {
        Client client = new Client();
        client.setEmail(clientCreateDto.getEmail());
        client.setFirstName(clientCreateDto.getFirstName());
        client.setLastName(clientCreateDto.getLastName());
        client.setUsername(clientCreateDto.getUsername());
        client.setPassword(clientCreateDto.getPassword());
        client.setActive(true);
        client.setConfirmed(false);
        client.setPoints(100);
        return client;
    }
}
