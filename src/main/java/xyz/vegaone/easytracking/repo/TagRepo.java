package xyz.vegaone.easytracking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.easytracking.domain.TagEntity;

@Repository
public interface TagRepo extends JpaRepository<TagEntity, Long> {

}
