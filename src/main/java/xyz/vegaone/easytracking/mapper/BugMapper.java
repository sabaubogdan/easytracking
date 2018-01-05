package xyz.vegaone.easytracking.mapper;

import org.mapstruct.Mapper;
import xyz.vegaone.easytracking.domain.BugEntity;
import xyz.vegaone.easytracking.dto.Bug;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface BugMapper {

    Bug domainToDto(BugEntity bugEntity);
    BugEntity dtoToDomain(Bug bug);
    List<Bug> domainToDtoList(List<BugEntity> bugEntities);
    List<BugEntity> dtoToDomainList(List<Bug> bugList);
}
