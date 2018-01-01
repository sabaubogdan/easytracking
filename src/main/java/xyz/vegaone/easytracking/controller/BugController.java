package xyz.vegaone.easytracking.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.easytracking.dto.Bug;
import xyz.vegaone.easytracking.service.BugService;

@RestController
@RequestMapping(value = "/api/bug")
@Slf4j
public class BugController {

    @Autowired
    private BugService bugService;

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Bug getBug(@PathVariable(value = "id") Long id){

        Bug bug = bugService.getBug(id);

        return bug;

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bug createBug(@RequestBody Bug bug){

        Bug createdBug = bugService.createBug(bug);

        return createdBug;

    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Bug updateBug(@RequestBody Bug bug){
        Bug updatedBug = bugService.updateBug(bug);

        return updatedBug;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void bugDeleted(@PathVariable(value = "id") Long id){

        bugService.deleteBug(id);
    }
}
