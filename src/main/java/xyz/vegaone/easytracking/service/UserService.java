package xyz.vegaone.easytracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytracking.domain.UserEntity;
import xyz.vegaone.easytracking.dto.User;
import xyz.vegaone.easytracking.mapper.UserMapper;
import xyz.vegaone.easytracking.repo.ProjectUserRepo;
import xyz.vegaone.easytracking.repo.UserRepo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepo userRepo;
    private UserMapper userMapper;
    private ProjectUserRepo projectUserRepo;

    @Autowired
    public UserService(UserRepo userRepo, UserMapper userMapper) {
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

    public User updateUser(User user) {
        UserEntity userEntity = userMapper.dtoToDomain(user);
        UserEntity savedUserEntity = userRepo.save(userEntity);

        return userMapper.domainToDto(savedUserEntity);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public List<User> findAll() {
        List<UserEntity> userEntityList = Collections.emptyList();
        userEntityList = userRepo.findAll();

        return userMapper.domainToDtoList(userEntityList);
    }

    public List<User> findAllByProjectId(Long id) {
        List<UserEntity> userEntityList = Collections.emptyList();

        userEntityList = projectUserRepo.findAllByProjectId(id);

        return userMapper.domainToDtoList(userEntityList);
    }

}
