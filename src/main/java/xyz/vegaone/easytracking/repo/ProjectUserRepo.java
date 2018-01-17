package xyz.vegaone.easytracking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.easytracking.domain.ProjectEntity;
import xyz.vegaone.easytracking.domain.ProjectUserEntity;
import xyz.vegaone.easytracking.domain.UserEntity;

import java.util.List;

@Repository
public interface ProjectUserRepo extends JpaRepository<ProjectUserEntity, Long>{

    List<ProjectEntity> findAllByUserId(Long id);
    List<UserEntity> findAllByProjectId(Long id);
}
