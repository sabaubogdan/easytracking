package xyz.vegaone.easytracking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.easytracking.domain.UserStoryEntity;
import xyz.vegaone.easytracking.dto.UserStory;

import java.util.List;

@Repository
public interface UserStoryRepo extends JpaRepository <UserStoryEntity, Long> {
        List<UserStoryEntity> findAllByProjectId(Long id);

        void deleteAllByProjectId(Long id);
}
