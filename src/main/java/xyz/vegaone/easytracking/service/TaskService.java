package xyz.vegaone.easytracking.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytracking.domain.TaskEntity;
import xyz.vegaone.easytracking.dto.Task;
import xyz.vegaone.easytracking.mapper.TaskMapper;
import xyz.vegaone.easytracking.repo.TaskRepo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TaskService {

    private TaskRepo taskRepo;

    private TaskMapper taskMapper;

    @Autowired
    public TaskService(TaskRepo taskRepo, TaskMapper taskMapper) {
        this.taskRepo = taskRepo;
        this.taskMapper = taskMapper;
    }

    public Task createTask(Task task) {
        log.info("Entering the service method createTask() having taskId: " + task.getId());

        TaskEntity taskEntity = taskMapper.dtoToDomain(task);

        TaskEntity savedTaskEntity = taskRepo.save(taskEntity);

        return taskMapper.domainToDto(savedTaskEntity);
    }

    public Task getTask(Long id) {
        log.info("Entering the service method getTask() having id: " + id);

        Optional<TaskEntity> taskOptional = taskRepo.findById(id);

        if (taskOptional.isPresent()) {
            TaskEntity taskEntity = taskOptional.get();

            return taskMapper.domainToDto(taskEntity);
        }

        return null;
    }

    public void deleteTask(Long id) {
        log.info("Entering the service method deleteTask() having id: " + id);

        taskRepo.deleteById(id);
    }

    public void deleteAllByUserStoryId(Long id){
        log.info("Entering the service method deleteAllByUserStoryId() having id: " + id);

        taskRepo.deleteAllByUserStoryId(id);
    }


    public Task updateTask(Task task) {
        log.info("Entering the service method updateTask() having taskId: " + task.getId());

        TaskEntity taskEntity = taskMapper.dtoToDomain(task);

        TaskEntity savedTaskEntity = taskRepo.save(taskEntity);

        return taskMapper.domainToDto(savedTaskEntity);
    }

    public List<Task> findAllByUserStoryId(Long userStoryId) {
        log.info("Entering the service method findAllByUserStoryId() having userStoryId: " + userStoryId);

        List<TaskEntity> taskEntityList = Collections.emptyList();

        taskEntityList = taskRepo.findAllByUserStoryId(userStoryId);

        return taskMapper.domainToDtoList(taskEntityList);
    }

}
