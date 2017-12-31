package xyz.vegaone.easytracking.mapper;

import org.mapstruct.Mapper;
import xyz.vegaone.easytracking.domain.ProjectEntity;
import xyz.vegaone.easytracking.dto.Project;

@Mapper(componentModel = "Spring")
public interface ProjectMapper {

    Project domainToDto(ProjectEntity projectEntity);
    ProjectEntity dtoToDomain(Project project);
}
