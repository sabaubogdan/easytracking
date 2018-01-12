package xyz.vegaone.easytracking.mapper;

import org.mapstruct.Mapper;
import xyz.vegaone.easytracking.domain.TagEntity;
import xyz.vegaone.easytracking.dto.Tag;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface TagMapper {
    Tag domainToDto(TagEntity tagEntity);

    TagEntity dtoToDomain(Tag tag);

    List<Tag> domainToDtoList(List<TagEntity> tagEntities);

    List<TagEntity> dtoToDomainList(List<Tag> tagList);
}
