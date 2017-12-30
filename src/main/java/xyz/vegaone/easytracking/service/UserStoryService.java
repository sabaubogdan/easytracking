package xyz.vegaone.easytracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytracking.domain.UserStoryEntity;
import xyz.vegaone.easytracking.dto.UserStory;
import xyz.vegaone.easytracking.mapper.UserStoryMapper;
import xyz.vegaone.easytracking.repo.UserStoryRepo;

import javax.jws.soap.SOAPBinding;
import java.util.Optional;

@Service
public class UserStoryService {

    @Autowired
    private UserStoryMapper userStoryMapper;

    @Autowired
    private UserStoryRepo userStoryRepo;

    public UserStory createUserStory(UserStory userStory){

        UserStoryEntity userStoryEntity = userStoryMapper.dtoToDomain(userStory);
        UserStoryEntity savedUserStoryEntity = userStoryRepo.save(userStoryEntity);
        UserStory savedUserStory = userStoryMapper.domainToDto(savedUserStoryEntity);

        return savedUserStory;
    }

    public UserStory getUserStory(Long id){
        Optional<UserStoryEntity> getResultOptional = userStoryRepo.findById(id);
        UserStoryEntity userStoryEntity = null;

        if (getResultOptional.isPresent()) {
            userStoryEntity = getResultOptional.get();
        }
        UserStory userStory = userStoryMapper.domainToDto(userStoryEntity);

        return userStory;
    }

    public void deleteUserStory(Long id){
        userStoryRepo.deleteById(id);
    }

    public UserStory updateUserStory(UserStory userStory){
        UserStoryEntity userStoryEntity = userStoryMapper.dtoToDomain(userStory);
        UserStoryEntity savedUserEntity =userStoryRepo.save(userStoryEntity);
        UserStory savedUserStory = userStoryMapper.domainToDto(savedUserEntity);

        return savedUserStory;
    }
}
