package wad.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.spring.domain.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
