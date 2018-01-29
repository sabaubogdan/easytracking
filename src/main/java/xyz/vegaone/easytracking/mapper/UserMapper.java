package xyz.vegaone.easytracking.mapper;

import org.mapstruct.Mapper;
import xyz.vegaone.easytracking.domain.UserEntity;
import xyz.vegaone.easytracking.dto.User;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    User domainToDto(UserEntity userEntity);

    List<User> domainToDtoList(List<UserEntity> userEntityList);

    UserEntity dtoToDomain(User user);

    List<UserEntity> dtoToDomainList(List<User> userList);
}
