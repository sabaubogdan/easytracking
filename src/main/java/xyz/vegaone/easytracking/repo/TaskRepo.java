package xyz.vegaone.easytracking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.easytracking.domain.TaskEntity;

@Repository
public interface TaskRepo extends JpaRepository<TaskEntity, Long> {
}
