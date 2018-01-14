package xyz.vegaone.easytracking.mapper;

import org.mapstruct.Mapper;
import xyz.vegaone.easytracking.domain.SprintEntity;
import xyz.vegaone.easytracking.dto.Sprint;

@Mapper(componentModel = "Spring")
public interface SprintMapper {

    Sprint domainToDto(SprintEntity sprintEntity);

    SprintEntity dtoToDomain(Sprint sprint);

}
