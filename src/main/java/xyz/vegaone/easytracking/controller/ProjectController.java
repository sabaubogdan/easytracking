package xyz.vegaone.easytracking.controller;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.easytracking.dto.Project;
import xyz.vegaone.easytracking.service.ProjectService;


@RestController
@RequestMapping(value = "api/project")
@Slf4j
@Log4j
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/{id}")
    public Project getProject(@PathVariable(value = "id") Long id) {
        Project project = projectService.getProject(id);

        return project;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);

        return createdProject;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Project updateProject(@RequestBody Project project) {
        Project updatedProject = projectService.updateProject(project);

        return updatedProject;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable(value = "id") Long id) {
        projectService.deleteProject(id);
    }
}
