package xyz.vegaone.easytracking.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytracking.domain.ProjectEntity;
import xyz.vegaone.easytracking.domain.UserStoryEntity;
import xyz.vegaone.easytracking.dto.Project;
import xyz.vegaone.easytracking.dto.UserStory;
import xyz.vegaone.easytracking.mapper.ProjectMapper;
import xyz.vegaone.easytracking.mapper.UserStoryMapper;
import xyz.vegaone.easytracking.repo.ProjectRepo;
import xyz.vegaone.easytracking.repo.UserStoryRepo;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProjectService {

    private ProjectMapper projectMapper;

    private ProjectRepo projectRepo;

    private UserStoryService userStoryService;

    private UserStoryRepo userStoryRepo;

    private UserStoryMapper userStoryMapper;

    @Autowired
    public ProjectService(ProjectMapper projectMapper, ProjectRepo projectRepo, UserStoryService userStoryService,
                          UserStoryRepo userStoryRepo, UserStoryMapper userStoryMapper) {
        this.projectMapper = projectMapper;
        this.projectRepo = projectRepo;
        this.userStoryService = userStoryService;
        this.userStoryRepo = userStoryRepo;
        this.userStoryMapper = userStoryMapper;
    }

    public Project createProject(Project project) {
        log.info("Entering the service method createProject() having projectId: " + project.getId());

        ProjectEntity projectEntity = projectMapper.dtoToDomain(project);
        ProjectEntity savedProject = projectRepo.save(projectEntity);

        Project projectDto = projectMapper.domainToDto(savedProject);


        return projectDto;
    }

    public Project getProject(Long id) {
        log.info("Entering the service method getProject() having id: " + id);

        Optional<ProjectEntity> projectOptional = projectRepo.findById(id);

        if (projectOptional.isPresent()) {
            ProjectEntity projectEntity = projectOptional.get();

            Project project = projectMapper.domainToDto(projectEntity);

            Optional<UserStoryEntity> userStoryOptional = userStoryRepo.findById(projectEntity.getId());

            UserStory userStory = null;

            if (userStoryOptional.isPresent()) {
                userStory = userStoryMapper.domainToDto(userStoryOptional.get());
            }

            return project;
        }

        return null;

    }

    public List<Project> getAllProjects() {
        log.info("Entering the service method getAllProjects() " );

        List<ProjectEntity> projectEntityList = projectRepo.findAll();

        return projectMapper.domainToDtoList(projectEntityList);
    }

    public void deleteProject(Long id) {
        log.info("Entering the service method deleteProject() having id " + id );

        userStoryService.deleteAllByProjectId(id);
        projectRepo.deleteById(id);
    }

    public Project updateProject(Project project) {
        log.info("Entering the service method updateProject() having projectId " + project.getId() );

        ProjectEntity projectEntity = projectMapper.dtoToDomain(project);

        ProjectEntity savedProjectEntity = projectRepo.save(projectEntity);
        Project savedProject = projectMapper.domainToDto(savedProjectEntity);

        return savedProject;

    }


}
