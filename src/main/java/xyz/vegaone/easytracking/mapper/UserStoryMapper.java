package xyz.vegaone.easytracking.mapper;

import org.mapstruct.Mapper;
import xyz.vegaone.easytracking.domain.UserStoryEntity;
import xyz.vegaone.easytracking.dto.UserStory;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserStoryMapper {

    UserStory domainToDto(UserStoryEntity userStoryEntity);
    List<UserStory> domainToDtoList(List<UserStoryEntity> userStoryEntityList);
    UserStoryEntity dtoToDomain(UserStory userStory);
    List<UserStoryEntity> dtoToDomainList(List<UserStory> userStoryList);
}
