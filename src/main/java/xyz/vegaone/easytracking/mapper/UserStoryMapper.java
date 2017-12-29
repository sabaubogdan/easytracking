package xyz.vegaone.easytracking.mapper;

import org.mapstruct.Mapper;
import xyz.vegaone.easytracking.domain.UserStoryEntity;
import xyz.vegaone.easytracking.dto.UserStory;

@Mapper(componentModel = "Spring")
public interface UserStoryMapper {

    UserStory domainToDto(UserStoryEntity userStoryEntity);
    UserStoryEntity dtoToDomain(UserStory userStory);
}
