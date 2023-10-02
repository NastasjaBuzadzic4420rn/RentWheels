package gui.mapper;

import gui.model.Manager;
import gui.restclient.dto.ManagerDto;

public class ManagerMapper {

    public Manager managerDtoToManager(ManagerDto managerDto){
        Manager manager = new Manager();
        manager.setId(managerDto.getId());
        manager.setUsername(managerDto.getUsername());
        manager.setEmail(managerDto.getEmail());
        manager.setBirthDate(managerDto.getBirthDate());
        manager.setFirstName(managerDto.getFirstName());
        manager.setLastName(managerDto.getLastName());
        manager.setStartedWorking(managerDto.getStartedWorking());
        manager.setActive(managerDto.isActive());
        manager.setConfirmed(managerDto.isConfirmed());
        return manager;
    }
}
