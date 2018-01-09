package xyz.vegaone.easytracking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.easytracking.domain.TagEntity;

import java.util.List;

@Repository
public interface TagRepo extends JpaRepository<TagEntity, Long> {

    List<TagEntity> findAllByUserStoryId(Long id);
    void deleteAllByUserStoryId(Long id);
    List<TagEntity> findAllByTaskId(Long id);
    void deleteAllByTaskId(Long id);
    List<TagEntity> findAllByBugId(Long id);
    void deleteAllByBugId(Long id);
}
