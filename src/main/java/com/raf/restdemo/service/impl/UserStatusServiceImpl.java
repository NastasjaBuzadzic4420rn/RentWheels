package com.raf.restdemo.service.impl;
import com.raf.restdemo.domain.Client;
import com.raf.restdemo.domain.UserStatus;
import com.raf.restdemo.dto.UserStatusDto;
import com.raf.restdemo.exception.NotFoundException;
import com.raf.restdemo.mapper.UserStatusMapper;
import com.raf.restdemo.repository.ClientRepository;
import com.raf.restdemo.repository.UserStatusRepository;
import com.raf.restdemo.service.UserStatusService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserStatusServiceImpl implements UserStatusService {

    private UserStatusMapper userStatusMapper;
    private UserStatusRepository userStatusRepository;
    private ClientRepository clientRepository;

    public UserStatusServiceImpl(UserStatusMapper userStatusMapper, UserStatusRepository userStatusRepository, ClientRepository clientRepository) {
        this.userStatusMapper = userStatusMapper;
        this.userStatusRepository = userStatusRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public UserStatusDto add(UserStatusDto userStatusDto) {
        UserStatus userStatus = userStatusMapper.userStatusDtoToUserStatus(userStatusDto);
        userStatusRepository.save(userStatus);
        return userStatusMapper.userStatusToUserStatusDto(userStatus);
    }

    @Override
    public Page<UserStatusDto> getForPoints(Long userId, Pageable pageable) {
        Client client = clientRepository.findById(userId).get();
        Page<UserStatus> statuses = userStatusRepository.findUserStatusByMaxPointsGreaterThanAndMinPointsLessThan(client.getPoints(), client.getPoints(), pageable).get();
        return statuses.map(userStatusMapper::userStatusToUserStatusDto);
    }

    @Override
    public Page<UserStatusDto> findAll(Pageable pageable) {
        return userStatusRepository.findAll(pageable)
                .map(userStatusMapper::userStatusToUserStatusDto);
    }

    @Override
    public UserStatusDto edit(Long id, UserStatusDto userStatusDto) {
        UserStatus userStatus = userStatusRepository.findById((id)).orElseThrow(() -> new NotFoundException(String
                .format("UserStatus with id: %d not found.", id)));
        userStatus.setDiscount(userStatusDto.getDiscount());
        userStatus.setRank(userStatusDto.getRank());
        userStatus.setMinPoints(userStatusDto.getMinPoints());
        userStatus.setMaxPoints(userStatusDto.getMaxPoints());
        userStatusRepository.save(userStatus);
        return userStatusMapper.userStatusToUserStatusDto(userStatus);
    }
}
