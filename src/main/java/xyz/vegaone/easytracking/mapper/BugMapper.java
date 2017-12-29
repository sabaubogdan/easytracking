package xyz.vegaone.easytracking.mapper;

import org.mapstruct.Mapper;
import xyz.vegaone.easytracking.domain.BugEntity;
import xyz.vegaone.easytracking.dto.Bug;

@Mapper(componentModel = "Spring")
public interface BugMapper {

    Bug domainToDto(BugEntity bugEntity);
    BugEntity dtoToDomain(Bug bug);
}
