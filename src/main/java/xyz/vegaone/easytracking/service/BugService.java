package xyz.vegaone.easytracking.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytracking.domain.BugEntity;
import xyz.vegaone.easytracking.dto.Bug;
import xyz.vegaone.easytracking.mapper.BugMapper;
import xyz.vegaone.easytracking.repo.BugRepo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BugService {

    private BugRepo bugRepo;

    private BugMapper bugMapper;

    @Autowired
    public BugService(BugRepo bugRepo,
                      BugMapper bugMapper) {
        this.bugRepo = bugRepo;
        this.bugMapper = bugMapper;
    }

    public Bug createBug(Bug bug) {
        log.info("Creating bug: " + bug.getId());

        BugEntity bugEntity = bugMapper.dtoToDomain(bug);

        BugEntity savedBugEntity = bugRepo.save(bugEntity);

        return bugMapper.domainToDto(savedBugEntity);
    }

    public Bug getBug(Long id) {
        log.info("Fetching bug with id: " + id);

        Optional<BugEntity> bugOptional = bugRepo.findById(id);

        if (bugOptional.isPresent()) {
            BugEntity bugEntity = bugOptional.get();

            return bugMapper.domainToDto(bugEntity);
        }

        return null;
    }

    public void deleteBug(Long id) {
        log.info("Deleting bug with id: " + id);


        bugRepo.deleteById(id);
    }

    public Bug updateBug(Bug bug) {
        log.info("Updating bug: " + bug.getId());

        BugEntity bugEntity = bugMapper.dtoToDomain(bug);

        BugEntity savedBugEntity = bugRepo.save(bugEntity);

        return bugMapper.domainToDto(savedBugEntity);
    }

    public List<Bug> findAllByUserStoryId(Long userStoryId) {
        log.info("Finding all bugs by userStoryId: " + userStoryId);

        List<BugEntity> bugEntityList = Collections.emptyList();

        bugEntityList = bugRepo.findAllByUserStoryId(userStoryId);

        return bugMapper.domainToDtoList(bugEntityList);
    }

    public void deleteAllByUserStoryId(Long id){
        log.info("Deleting all bugs by userStoryId: " + id);

        bugRepo.deleteAllByUserStoryId(id);
    }
}
