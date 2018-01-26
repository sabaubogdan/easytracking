package xyz.vegaone.easytracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytracking.domain.UserEntity;
import xyz.vegaone.easytracking.dto.User;
import xyz.vegaone.easytracking.mapper.UserMapper;
import xyz.vegaone.easytracking.repo.UserRepo;

import java.util.Optional;

@Service
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

        UserEntity userEntity = userMapper.dtoToDomain(user);

        UserEntity savedUserEntity = userRepo.save(userEntity);

        return userMapper.domainToDto(savedUserEntity);
    }

    public User getUser(Long id) {
        Optional<UserEntity> userOptional = userRepo.findById(id);

        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();

            return userMapper.domainToDto(userEntity);
        }

        return null;
    }

    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }

    public User updateUser(User user) {
        UserEntity userEntity = userMapper.dtoToDomain(user);

        UserEntity savedUserEntity = userRepo.save(userEntity);

        return userMapper.domainToDto(savedUserEntity);
    }
}
