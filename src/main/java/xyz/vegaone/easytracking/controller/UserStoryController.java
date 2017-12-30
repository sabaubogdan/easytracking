package xyz.vegaone.easytracking.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.easytracking.dto.UserStory;
import xyz.vegaone.easytracking.service.UserStoryService;

import javax.jws.soap.SOAPBinding;

@RestController
@RequestMapping(value = "/userstory")
@Slf4j
public class UserStoryController {

    @Autowired
    private UserStoryService userStoryService;

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public UserStory getUserStory(@PathVariable(value = "id") Long id){

        UserStory userStory = userStoryService.getUserStory(id);

        return userStory;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserStory createUserStory(@RequestBody UserStory userStory){

        UserStory createdUserStory = userStoryService.createUserStory(userStory);

        return createdUserStory;

    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserStory updateUserStory(@RequestBody UserStory userStory){

        UserStory updatedUserStory = userStoryService.updateUserStory(userStory);

        return updatedUserStory;

    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletedUserStory(@PathVariable(value = "id") Long id){
        userStoryService.deleteUserStory(id);
    }

}
