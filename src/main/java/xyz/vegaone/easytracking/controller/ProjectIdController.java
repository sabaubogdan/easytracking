package xyz.vegaone.easytracking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.easytracking.dto.UserStory;
import xyz.vegaone.easytracking.service.ProjectService;
import xyz.vegaone.easytracking.service.UserStoryService;

@RestController
@RequestMapping(value = "/userstory")
@Slf4j
public class ProjectIdController {

    @Autowired
    private UserStoryService userStoryService;

    @GetMapping(value = "/project/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public UserStory getUserStory(@PathVariable(value = "id") Long id){

        UserStory userStory = userStoryService.getUserStory(id);

        return userStory;
    }
}
