package xyz.vegaone.easytracking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.easytracking.domain.BugEntity;

import java.util.List;

@Repository
public interface BugRepo extends JpaRepository<BugEntity, Long> {

    List<BugEntity> findAllByUserStoryId(Long id);

    void deleteAllByUserStoryId(Long id);
}
