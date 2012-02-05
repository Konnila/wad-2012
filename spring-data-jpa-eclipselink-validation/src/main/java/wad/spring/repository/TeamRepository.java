package wad.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.spring.domain.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
