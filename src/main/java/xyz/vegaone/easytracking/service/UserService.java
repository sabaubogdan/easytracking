package xyz.vegaone.easytracking.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytracking.domain.UserEntity;
import xyz.vegaone.easytracking.dto.User;
import xyz.vegaone.easytracking.mapper.UserMapper;
import xyz.vegaone.easytracking.repo.UserRepo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private UserRepo userRepo;

    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepo userRepo,
                       UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    public User createUser(User user) {
        log.info("Entering the service method createUser() having userId: " + user.getId());

        UserEntity userEntity = userMapper.dtoToDomain(user);

        UserEntity savedUserEntity = userRepo.save(userEntity);

        return userMapper.domainToDto(savedUserEntity);
    }

    public User getUser(Long id) {
        log.info("Entering the service method getUser() having id: " + id);
        Optional<UserEntity> userOptional = userRepo.findById(id);

        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();

            return userMapper.domainToDto(userEntity);
        }

        return null;
    }

    public void deleteUserById(Long id) {
        log.info("Entering the service method deleteUserById() having id: " + id);
        userRepo.deleteById(id);
    }

    public User updateUser(User user) {
        log.info("Entering the service method updateUser() having userId: " + user.getId());
        UserEntity userEntity = userMapper.dtoToDomain(user);

        UserEntity savedUserEntity = userRepo.save(userEntity);

        return userMapper.domainToDto(savedUserEntity);
    }

    public List<User> findAll(){
        log.info("Entering the service method findAll() ");
        List<UserEntity> userEntityList = Collections.emptyList();

        userEntityList = userRepo.findAll();

        return userMapper.domainToDtoList(userEntityList);
    }
}
