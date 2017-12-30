package xyz.vegaone.easytracking.service;

//import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytracking.domain.TaskEntity;
import xyz.vegaone.easytracking.domain.UserStoryEntity;
import xyz.vegaone.easytracking.dto.Task;
import xyz.vegaone.easytracking.dto.UserStory;
import xyz.vegaone.easytracking.mapper.TaskMapper;
import xyz.vegaone.easytracking.mapper.UserStoryMapper;
import xyz.vegaone.easytracking.repo.TaskRepo;
import xyz.vegaone.easytracking.repo.UserStoryRepo;

import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UserStoryRepo userStoryRepo;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private UserStoryMapper userStoryMapper;

    public Task createTask(Task task){

        TaskEntity taskEntity = taskMapper.dtoToDomain(task);
        taskEntity.setUserStoryId(task.getUserStory().getId());

        TaskEntity savedTaskEntity = taskRepo.save(taskEntity);
        Task savedTask = taskMapper.domainToDto(savedTaskEntity);
        savedTask.setUserStory(task.getUserStory());

        return savedTask;
    }

    public Task getTask(Long id){
        Optional<TaskEntity> taskOptional = taskRepo.findById(id);

        if (taskOptional.isPresent()) {
            TaskEntity taskEntity = taskOptional.get();

            Task task = taskMapper.domainToDto(taskEntity);

            Optional<UserStoryEntity> userStoryOptional = userStoryRepo.findById(taskEntity.getUserStoryId());

            UserStory userStory = null;

            if (userStoryOptional.isPresent()) {
                userStory = userStoryMapper.domainToDto(userStoryOptional.get());
            }

            task.setUserStory(userStory);

            return task;
        }

        return null;
    }

    public void deleteTask(Long id){
        taskRepo.deleteById(id);
    }

    public Task updateTask(Task task){
        TaskEntity taskEntity = taskMapper.dtoToDomain(task);
        taskEntity.setUserStoryId(task.getUserStory().getId());

        TaskEntity savedTaskEntity = taskRepo.save(taskEntity);
        Task savedTask = taskMapper.domainToDto(savedTaskEntity);
        savedTask.setUserStory(task.getUserStory());

        return savedTask;
    }

}
