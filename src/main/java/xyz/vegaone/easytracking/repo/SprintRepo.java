package xyz.vegaone.easytracking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.vegaone.easytracking.domain.SprintEntity;

public interface SprintRepo extends JpaRepository<SprintEntity,Long> {

}
