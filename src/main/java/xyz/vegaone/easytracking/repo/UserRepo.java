package xyz.vegaone.easytracking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.easytracking.domain.UserEntity;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long>{

    List<UserEntity> findAllByProjectId(Long id);
}