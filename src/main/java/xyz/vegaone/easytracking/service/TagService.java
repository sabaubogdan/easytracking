package xyz.vegaone.easytracking.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytracking.domain.TagEntity;
import xyz.vegaone.easytracking.dto.Tag;
import xyz.vegaone.easytracking.mapper.TagMapper;
import xyz.vegaone.easytracking.repo.TagRepo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TagService {

    private TagMapper tagMapper;

    private TagRepo tagRepo;

    @Autowired
    public TagService(TagMapper tagMapper, TagRepo tagRepo) {
        this.tagMapper = tagMapper;
        this.tagRepo = tagRepo;
    }

    public Tag createTag(Tag tag){
        log.info("Creating tag: " + tag);

        TagEntity tagEntity = tagMapper.dtoToDomain(tag);

        TagEntity savedTagEntity = tagRepo.save(tagEntity);

        return tagMapper.domainToDto(savedTagEntity);
    }

    public Tag getTag(Long id){
        log.info("Fetching tag with id: " + id);

        Optional<TagEntity> tagOptional = tagRepo.findById(id);

        if (tagOptional.isPresent()){
            TagEntity tagEntity = tagOptional.get();

            return tagMapper.domainToDto(tagEntity);
        }
        return null;
    }

    public void deleteTag(Long id){
        log.info("Deleting tag with id: " + id);

        tagRepo.deleteById(id);
    }

    public Tag updateTag(Tag tag){
        log.info("Updating tag: " + tag.getId());

        TagEntity tagEntity = tagMapper.dtoToDomain(tag);

        TagEntity savedTagEntity = tagRepo.save(tagEntity);

        return tagMapper.domainToDto(savedTagEntity);
    }
    public List<Tag> getAllTagsByTaskId(Long taskId){
        log.info("Fetching all tags by task id: " +taskId);

        List<TagEntity> tagEntityList = Collections.emptyList();

        tagEntityList = tagRepo.findAllByTaskId(taskId);

        return tagMapper.domainToDtoList(tagEntityList);
    }
    public void deleteAllByTaskId(Long id){
        log.info("Deleting all tags by task id: " + id);

        tagRepo.deleteAllByTaskId(id);
    }

    public List<Tag> getAllTagsByUserStorId(Long userStoryId){
        log.info("Fetching all tags by userStory id " + userStoryId);

        List<TagEntity> tagEntityList = Collections.emptyList();

        tagEntityList = tagRepo.findAllByUserStoryId(userStoryId);

        return tagMapper.domainToDtoList(tagEntityList);
    }
    public void deleteAllByUserStoryId(Long id){
        log.info("Delete all tags by user story id: " + id);

        tagRepo.deleteAllByUserStoryId(id);
    }

    public List<Tag> getAllTagsByBugId(Long bugId){
        log.info("Fetching all tags by bug id: " + bugId);

        List<TagEntity> tagEntityList = Collections.emptyList();

        tagEntityList = tagRepo.findAllByBugId(bugId);

        return tagMapper.domainToDtoList(tagEntityList);
    }
    public void deleteAllByBugId(Long id){
        log.info("Delete all tags by bug id: " + id);

        tagRepo.deleteAllByBugId(id);
    }


}