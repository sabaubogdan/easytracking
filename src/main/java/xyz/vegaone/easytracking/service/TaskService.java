package xyz.vegaone.easytracking.service;

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
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private TaskMapper taskMapper;

    public Task createTask(Task task) {

        TaskEntity taskEntity = taskMapper.dtoToDomain(task);

        TaskEntity savedTaskEntity = taskRepo.save(taskEntity);

        return taskMapper.domainToDto(savedTaskEntity);
    }

    public Task getTask(Long id) {
        Optional<TaskEntity> taskOptional = taskRepo.findById(id);

        if (taskOptional.isPresent()) {
            TaskEntity taskEntity = taskOptional.get();

            return taskMapper.domainToDto(taskEntity);
        }

        return null;
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }
    public void deleteAllByUserStoryId(Long id){
        taskRepo.deleteAllByUserStoryId(id);
    }


    public Task updateTask(Task task) {
        TaskEntity taskEntity = taskMapper.dtoToDomain(task);

        TaskEntity savedTaskEntity = taskRepo.save(taskEntity);

        return taskMapper.domainToDto(savedTaskEntity);
    }

    public List<Task> findAllByUserStoryId(Long userStoryId) {
        List<TaskEntity> taskEntityList = Collections.emptyList();

        taskEntityList = taskRepo.findAllByUserStoryId(userStoryId);

        return taskMapper.domainToDtoList(taskEntityList);
    }

}
