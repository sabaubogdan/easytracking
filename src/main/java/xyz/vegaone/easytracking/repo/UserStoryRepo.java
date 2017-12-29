package xyz.vegaone.easytracking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.easytracking.domain.UserStoryEntity;

@Repository
public interface UserStoryRepo extends JpaRepository <UserStoryEntity, Long> {

//    UserStoryEntity findAllByDescriptionAndTitle(String description, String title);
}
