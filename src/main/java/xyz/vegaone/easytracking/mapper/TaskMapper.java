package xyz.vegaone.easytracking.mapper;

import org.mapstruct.Mapper;
import xyz.vegaone.easytracking.domain.TaskEntity;
import xyz.vegaone.easytracking.dto.Task;

@Mapper(componentModel = "Spring")
public interface TaskMapper {

    Task domainToDto(TaskEntity taskEntity);
    TaskEntity dtoToDomain(Task task);
}
