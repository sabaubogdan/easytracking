package xyz.vegaone.easytracking.mapper;

import org.mapstruct.Mapper;
import xyz.vegaone.easytracking.domain.TaskEntity;
import xyz.vegaone.easytracking.dto.Task;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface TaskMapper {

    Task domainToDto(TaskEntity taskEntity);
    TaskEntity dtoToDomain(Task task);
    List<Task> domainToDtoList(List<TaskEntity> taskEntities);
    List<TaskEntity> dtoToDomainList(List<Task> taskList);
}
