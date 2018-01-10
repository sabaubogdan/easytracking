package xyz.vegaone.easytracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytracking.domain.SprintEntity;
import xyz.vegaone.easytracking.dto.Sprint;
import xyz.vegaone.easytracking.mapper.SprintMapper;
import xyz.vegaone.easytracking.mapper.UserStoryMapper;
import xyz.vegaone.easytracking.repo.SprintRepo;
import xyz.vegaone.easytracking.repo.UserStoryRepo;

import java.util.Collections;
import java.util.Optional;

@Service
public class SprintService {

    private SprintRepo sprintRepo;

    private SprintMapper sprintMapper;

    private UserStoryService userStoryService;

    private UserStoryRepo userStoryRepo;

    private UserStoryMapper userStoryMapper;

    @Autowired
    public SprintService(SprintRepo sprintRepo, SprintMapper sprintMapper, UserStoryService userStoryService, UserStoryRepo userStoryRepo, UserStoryMapper userStoryMapper) {
        this.sprintRepo = sprintRepo;
        this.sprintMapper = sprintMapper;
        this.userStoryService = userStoryService;
        this.userStoryRepo = userStoryRepo;
        this.userStoryMapper = userStoryMapper;
    }



    public Sprint createSprint(Sprint sprint){
        SprintEntity sprintEntity = sprintMapper.dtoToDomain(sprint);
        SprintEntity savedSprintEntity = sprintRepo.save(sprintEntity);
        Sprint savedSprint = sprintMapper.domainToDto(savedSprintEntity);

        Long sprintNumber = savedSprintEntity.getId() + 1;

        savedSprint.setUserStoryList(Collections.emptyList());

       return savedSprint;
    }

    public Sprint getSprint(Long id){
        Optional<SprintEntity> getResultOptional = sprintRepo.findById(id);
        SprintEntity sprintEntity = null;

        if (getResultOptional.isPresent()) {
            sprintEntity = getResultOptional.get();
        }
            Sprint sprint = sprintMapper.domainToDto(sprintEntity);

        if (sprint != null){
            sprint.setUserStoryList(userStoryService.findAllBySprintId(id));
        }

        return sprint;

    }

    public void deleteSprint(Long id){
        sprintRepo.deleteById(id);
    }

    public Sprint updateSprint(Sprint sprint){
        SprintEntity sprintEntity = sprintMapper.dtoToDomain(sprint);
        SprintEntity savedSprintEntity = sprintRepo.save(sprintEntity);

        Sprint updatedSprint = sprintMapper.domainToDto(savedSprintEntity);

        return updatedSprint;
    }

}
