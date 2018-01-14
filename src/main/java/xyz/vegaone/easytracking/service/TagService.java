package xyz.vegaone.easytracking.service;


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
public class TagService {

    private TagMapper tagMapper;

    private TagRepo tagRepo;

    @Autowired
    public TagService(TagMapper tagMapper, TagRepo tagRepo) {
        this.tagMapper = tagMapper;
        this.tagRepo = tagRepo;
    }

    public Tag createTag(Tag tag){
        TagEntity tagEntity = tagMapper.dtoToDomain(tag);

        TagEntity savedTagEntity = tagRepo.save(tagEntity);

        return tagMapper.domainToDto(savedTagEntity);
    }

    public Tag getTag(Long id){
        Optional<TagEntity> tagOptional = tagRepo.findById(id);

        if (tagOptional.isPresent()){
            TagEntity tagEntity = tagOptional.get();

            return tagMapper.domainToDto(tagEntity);
        }
        return null;
    }

    public void deleteTag(Long id){
        tagRepo.deleteById(id);
    }

    public Tag updateTag(Tag tag){
        TagEntity tagEntity = tagMapper.dtoToDomain(tag);

        TagEntity savedTagEntity = tagRepo.save(tagEntity);

        return tagMapper.domainToDto(savedTagEntity);
    }
    public List<Tag> getAllTagsByTaskId(Long taskId){
        List<TagEntity> tagEntityList = Collections.emptyList();

        tagEntityList = tagRepo.findAllByTaskId(taskId);

        return tagMapper.domainToDtoList(tagEntityList);
    }
    public void deleteAllByTaskId(Long id){
        tagRepo.deleteAllByTaskId(id);
    }

    public List<Tag> getAllTagsByUserStorId(Long userStoryId){
        List<TagEntity> tagEntityList = Collections.emptyList();

        tagEntityList = tagRepo.findAllByUserStoryId(userStoryId);

        return tagMapper.domainToDtoList(tagEntityList);
    }
    public void deleteAllByUserStoryId(Long id){
        tagRepo.deleteAllByUserStoryId(id);
    }

    public List<Tag> getAllTagsByBugId(Long bugId){
        List<TagEntity> tagEntityList = Collections.emptyList();

        tagEntityList = tagRepo.findAllByBugId(bugId);

        return tagMapper.domainToDtoList(tagEntityList);
    }
    public void deleteAllByBugId(Long id){
        tagRepo.deleteAllByBugId(id);
    }


}