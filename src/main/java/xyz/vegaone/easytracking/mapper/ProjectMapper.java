package xyz.vegaone.easytracking.mapper;

import org.mapstruct.Mapper;
import xyz.vegaone.easytracking.domain.ProjectEntity;
import xyz.vegaone.easytracking.dto.Project;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface ProjectMapper {

    Project domainToDto(ProjectEntity projectEntity);
    ProjectEntity dtoToDomain(Project project);
    List<Project> domainToDtoList(List<ProjectEntity> projectList);
    List<ProjectEntity> dtoToDomainList(List<Project> projectList);
}
