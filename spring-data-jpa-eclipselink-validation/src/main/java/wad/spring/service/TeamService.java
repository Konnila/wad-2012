package wad.spring.service;

import java.util.List;
import wad.spring.domain.Team;

public interface TeamService {
    Team findOne(Long id);
    void create(Team team);
    List<Team> list();
}
