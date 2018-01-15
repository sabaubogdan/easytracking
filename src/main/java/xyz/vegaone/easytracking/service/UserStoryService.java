package xyz.vegaone.easytracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.vegaone.easytracking.domain.UserStoryEntity;
import xyz.vegaone.easytracking.dto.UserStory;
import xyz.vegaone.easytracking.mapper.UserStoryMapper;
import xyz.vegaone.easytracking.repo.UserStoryRepo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserStoryService {

    private UserStoryMapper userStoryMapper;

    private UserStoryRepo userStoryRepo;

    private TaskService taskService;

    private BugService bugService;

    @Autowired
    public UserStoryService(UserStoryMapper userStoryMapper,
                            UserStoryRepo userStoryRepo,
                            TaskService taskService,
                            BugService bugService) {
        this.userStoryMapper = userStoryMapper;
        this.userStoryRepo = userStoryRepo;
        this.taskService = taskService;
        this.bugService = bugService;
    }

    public List<UserStory> findAllByProjectId(Long id) {
        List<UserStoryEntity> userStoryEntityList = userStoryRepo.findAllByProjectId(id);

        List<UserStory> userStoryList = userStoryMapper.domainToDtoList(userStoryEntityList);

        for (UserStory userStory : userStoryList) {
            userStory.setTaskList(taskService.findAllByUserStoryId(userStory.getId()));
        }

        for (UserStory userStory : userStoryList) {
            userStory.setBugList(bugService.findAllByUserStoryId(userStory.getId()));
        }

        return userStoryList;
    }

    public List<UserStory> findAllBySprintId(Long id) {
        List<UserStoryEntity> userStoryEntityList = userStoryRepo.findAllBySprintId(id);

        List<UserStory> userStoryList = userStoryMapper.domainToDtoList(userStoryEntityList);

        return userStoryList;

    }

    public void deleteAllByProjectId(Long id) {
        taskService.deleteAllByUserStoryId(id);
        userStoryRepo.deleteAllByProjectId(id);
    }

    public UserStory createUserStory(UserStory userStory) {

        UserStoryEntity userStoryEntity = userStoryMapper.dtoToDomain(userStory);
        UserStoryEntity savedUserStoryEntity = userStoryRepo.save(userStoryEntity);
        UserStory savedUserStory = userStoryMapper.domainToDto(savedUserStoryEntity);
        savedUserStory.setTaskList(Collections.emptyList());
        savedUserStory.setBugList(Collections.emptyList());

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

        if (userStory != null) {
            userStory.setBugList(bugService.findAllByUserStoryId(id));
        }

        return userStory;
    }

    @Transactional
    public void deleteUserStory(Long id) {
        taskService.deleteAllByUserStoryId(id);
        bugService.deleteAllByUserStoryId(id);
        userStoryRepo.deleteById(id);
    }

    public UserStory updateUserStory(UserStory userStory) {
        UserStoryEntity userStoryEntity = userStoryMapper.dtoToDomain(userStory);
        UserStoryEntity savedUserEntity = userStoryRepo.save(userStoryEntity);

        UserStory updatedUserStory = userStoryMapper.domainToDto(savedUserEntity);
        updatedUserStory.setTaskList(taskService.findAllByUserStoryId(updatedUserStory.getId()));
        updatedUserStory.setBugList(bugService.findAllByUserStoryId(updatedUserStory.getId()));

        return updatedUserStory;
    }

    public List<UserStory> findAllByProjectIdAndSprintId(Long projectId, Long sprintId) {
        List<UserStoryEntity> userStoryEntityList = userStoryRepo.findAllByProjectIdAndSprintId(projectId, sprintId);

        List<UserStory> userStoryList = userStoryMapper.domainToDtoList(userStoryEntityList);

        return userStoryList;
    }
}
