package xyz.vegaone.easytracking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.easytracking.domain.BugEntity;

@Repository
public interface BugRepo extends JpaRepository<BugEntity, Long> {
}
