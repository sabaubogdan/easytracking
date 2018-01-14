package xyz.vegaone.easytracking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.easytracking.dto.Sprint;
import xyz.vegaone.easytracking.service.SprintService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/sprint")
@Slf4j
public class SprintController {

    private SprintService sprintService;

    @Autowired
    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Sprint getSprint(@PathVariable(value = "id") Long id) {

        Sprint sprint = sprintService.getSprint(id);

        return sprint;

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sprint createSprint(@RequestBody Sprint sprint) {
        Sprint createdSprint = sprintService.createSprint(sprint);

        return createdSprint;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Sprint updateSprint(@RequestBody Sprint sprint) {
        Sprint updatedSprint = sprintService.updateSprint(sprint);

        return updatedSprint;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSprint(@PathVariable(value = "id") Long id) {
        sprintService.deleteSprint(id);

    }

    @GetMapping(value = "/project/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Sprint> getSprintListByProjectId(@PathVariable(value = "id") Long projectId){

        List<Sprint> sprintList = sprintService.findAllByProjectId(projectId);

        return sprintList;
    }
}
