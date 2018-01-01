package xyz.vegaone.easytracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytracking.domain.ProjectEntity;
import xyz.vegaone.easytracking.domain.TaskEntity;
import xyz.vegaone.easytracking.domain.UserStoryEntity;
import xyz.vegaone.easytracking.dto.Project;
import xyz.vegaone.easytracking.dto.Task;
import xyz.vegaone.easytracking.dto.UserStory;
import xyz.vegaone.easytracking.mapper.ProjectMapper;
import xyz.vegaone.easytracking.mapper.UserStoryMapper;
import xyz.vegaone.easytracking.repo.ProjectRepo;
import xyz.vegaone.easytracking.repo.UserStoryRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private UserStoryService userStoryService;

    @Autowired
    private UserStoryRepo userStoryRepo;

    @Autowired
    private UserStoryMapper userStoryMapper;

    public Project createProject(Project project){
        ProjectEntity projectEntity = projectMapper.dtoToDomain(project);
        ProjectEntity savedProject = projectRepo.save(projectEntity);

        Project projectDto = projectMapper.domainToDto(savedProject);


        return projectDto;
    }

    public Project getProject(Long id){
//        ProjectEntity projectEntity = projectRepo.getOne(id);
//        Project projectDto = projectMapper.domainToDto(projectEntity);
//
//        List<UserStory> userStoryList = userStoryService.findAllByProjectId(id);
//
//        projectDto.setUserStories(userStoryList);
//
//        return projectDto;
        Optional<ProjectEntity> projectOptional = projectRepo.findById(id);

        if (projectOptional.isPresent()) {
            ProjectEntity projectEntity = projectOptional.get();

            Project project = projectMapper.domainToDto(projectEntity);

            Optional<UserStoryEntity> userStoryOptional = userStoryRepo.findById(projectEntity.getId());

            UserStory userStory = null;

            if (userStoryOptional.isPresent()) {
                userStory = userStoryMapper.domainToDto(userStoryOptional.get());
            }

           // project.setUserStories(userStory);

            return project;
        }

        return null;

    }

    public void deleteProject(Long id){
        userStoryService.deleteAllByProjectId(id);
        projectRepo.deleteById(id);
    }

    public Project updateProject(Project project){
        ProjectEntity projectEntity = projectMapper.dtoToDomain(project);

        ProjectEntity savedProjectEntity = projectRepo.save(projectEntity);
        Project savedProject = projectMapper.domainToDto(savedProjectEntity);

        return savedProject;

    }


}
