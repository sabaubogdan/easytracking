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

import java.util.Optional;

@Service
public class BugService {

    private BugRepo bugRepo;

    private UserStoryRepo userStoryRepo;

    private BugMapper bugMapper;

    private UserStoryMapper userStoryMapper;

    @Autowired
    public BugService(BugRepo bugRepo, UserStoryRepo userStoryRepo, BugMapper bugMapper, UserStoryMapper userStoryMapper) {
        this.bugRepo = bugRepo;
        this.userStoryRepo = userStoryRepo;
        this.bugMapper = bugMapper;
        this.userStoryMapper = userStoryMapper;
    }

    public Bug createBug(Bug bug) {

        BugEntity bugEntity = bugMapper.dtoToDomain(bug);
        bugEntity.setUserStoryId(bug.getUserStory().getId());

        BugEntity savedBugEntity = bugRepo.save(bugEntity);
        Bug savedBug = bugMapper.domainToDto(savedBugEntity);
        savedBug.setUserStory(bug.getUserStory());

        return savedBug;
    }

    public Bug getBug(Long id) {
        Optional<BugEntity> bugOptional = bugRepo.findById(id);

        if (bugOptional.isPresent()) {
            BugEntity bugEntity = bugOptional.get();
            Bug bug = bugMapper.domainToDto(bugEntity);

            Optional<UserStoryEntity> userStoryOptional = userStoryRepo.findById(bugEntity.getUserStoryId());

            if (userStoryOptional.isPresent()) {
                UserStoryEntity userStoryEntity = userStoryOptional.get();
                UserStory userStory = userStoryMapper.domainToDto(userStoryEntity);

                bug.setUserStory(userStory);
            }

            return bug;
        }

        return null;
    }

    public void deleteBug(Long id) {
        bugRepo.deleteById(id);
    }

    public Bug updateBug(Bug bug) {
        BugEntity bugEntity = bugMapper.dtoToDomain(bug);
        bugEntity.setUserStoryId(bug.getUserStory().getId());

        BugEntity savedBugEntity = bugRepo.save(bugEntity);
        Bug savedBug = bugMapper.domainToDto(savedBugEntity);
        savedBug.setUserStory(bug.getUserStory());

        return savedBug;
    }

}
