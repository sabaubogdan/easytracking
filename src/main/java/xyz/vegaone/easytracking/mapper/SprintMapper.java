package xyz.vegaone.easytracking.mapper;

import org.mapstruct.Mapper;
import xyz.vegaone.easytracking.domain.SprintEntity;
import xyz.vegaone.easytracking.dto.Sprint;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface SprintMapper {

    Sprint domainToDto(SprintEntity sprintEntity);

    SprintEntity dtoToDomain(Sprint sprint);

    List<Sprint> domainToDtoList(List<SprintEntity> sprintEntityList);

    List<SprintEntity> dtoToDomainList(List<Sprint> sprintList);
}
