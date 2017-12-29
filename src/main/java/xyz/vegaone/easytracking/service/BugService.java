package xyz.vegaone.easytracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytracking.domain.BugEntity;
import xyz.vegaone.easytracking.domain.UserStoryEntity;
import xyz.vegaone.easytracking.dto.Bug;
import xyz.vegaone.easytracking.dto.UserStory;
import xyz.vegaone.easytracking.mapper.BugMapper;
import xyz.vegaone.easytracking.mapper.UserStoryMapper;
import xyz.vegaone.easytracking.repo.BugRepo;
import xyz.vegaone.easytracking.repo.UserStoryRepo;

@Service
public class BugService {

    @Autowired
    private BugRepo bugRepo;

    @Autowired
    private UserStoryRepo userStoryRepo;

    @Autowired
    private BugMapper bugMapper;

    @Autowired
    private UserStoryMapper userStoryMapper;

    public Bug createBug(Bug bug){

        BugEntity bugEntity = bugMapper.dtoToDomain(bug);
        bugEntity.setUserStoryId(bug.getUserStory().getId());

        BugEntity savedBugEntity = bugRepo.save(bugEntity);
        Bug savedBug = bugMapper.domainToDto(savedBugEntity);
        savedBug.setUserStory(bug.getUserStory());

        return savedBug;
    }

    public Bug getBug(Long id){
        BugEntity bugEntity = bugRepo.findById(id).get();
        Bug bug = bugMapper.domainToDto(bugEntity);

        UserStoryEntity userStoryEntity = userStoryRepo.findById(bugEntity.getUserStoryId()).get();
        UserStory userStory = userStoryMapper.domainToDto(userStoryEntity);

        bug.setUserStory(userStory);

        return bug;
    }

    public void deleteBug(Long id){
        bugRepo.deleteById(id);
    }

    public Bug updateBug(Bug bug){
        BugEntity bugEntity = bugMapper.dtoToDomain(bug);
        bugEntity.setUserStoryId(bug.getUserStory().getId());

        BugEntity savedBugEntity = bugRepo.save(bugEntity);
        Bug savedBug = bugMapper.domainToDto(savedBugEntity);
        savedBug.setUserStory(bug.getUserStory());

        return savedBug;
    } 

}
