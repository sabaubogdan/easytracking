package xyz.vegaone.easytracking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.vegaone.easytracking.domain.SprintEntity;

import java.util.List;

public interface SprintRepo extends JpaRepository<SprintEntity,Long> {

    List<SprintEntity> findAllByProjectId(Long projectId);
}
