package xyz.vegaone.easytracking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.easytracking.domain.ProjectEntity;

@Repository
public interface ProjectRepo extends JpaRepository<ProjectEntity, Long> {
}
