package xyz.vegaone.easytracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytracking.domain.UserStoryEntity;
import xyz.vegaone.easytracking.dto.UserStory;
import xyz.vegaone.easytracking.mapper.UserStoryMapper;
import xyz.vegaone.easytracking.repo.UserStoryRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserStoryService {

    @Autowired
    private UserStoryMapper userStoryMapper;

    @Autowired
    private UserStoryRepo userStoryRepo;

    @Autowired
    private TaskService taskService;

    public List<UserStory> findAllByProjectId(Long id) {
        List<UserStoryEntity> userStoryEntityList = userStoryRepo.findAllByProjectId(id);

        List<UserStory> userStoryList = userStoryMapper.domainToDtoList(userStoryEntityList);

        for (UserStory userStory : userStoryList) {
            userStory.setTaskList(taskService.findAllByUserStoryId(userStory.getId()));
        }

        return userStoryList;
    }

    public void deleteAllByProjectId(Long id) {
        //delete tasks asociated with the user stories first
        userStoryRepo.deleteAllByProjectId(id);
    }

    public UserStory createUserStory(UserStory userStory) {

        UserStoryEntity userStoryEntity = userStoryMapper.dtoToDomain(userStory);
        UserStoryEntity savedUserStoryEntity = userStoryRepo.save(userStoryEntity);
        UserStory savedUserStory = userStoryMapper.domainToDto(savedUserStoryEntity);

        return savedUserStory;
    }

    public UserStory getUserStory(Long id) {
        Optional<UserStoryEntity> getResultOptional = userStoryRepo.findById(id);
        UserStoryEntity userStoryEntity = null;

        if (getResultOptional.isPresent()) {
            userStoryEntity = getResultOptional.get();
        }

        UserStory userStory = userStoryMapper.domainToDto(userStoryEntity);

        if (userStory != null) {
            userStory.setTaskList(taskService.findAllByUserStoryId(id));
        }

        return userStory;
    }

    public void deleteUserStory(Long id) {
        taskService.deleteAllByUserStoryId(id);
        userStoryRepo.deleteById(id);
    }

    public UserStory updateUserStory(UserStory userStory) {
        UserStoryEntity userStoryEntity = userStoryMapper.dtoToDomain(userStory);
        UserStoryEntity savedUserEntity = userStoryRepo.save(userStoryEntity);

        return userStoryMapper.domainToDto(savedUserEntity);
    }
}
