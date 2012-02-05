package wad.spring.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.spring.domain.Player;
import wad.spring.domain.Team;
import wad.spring.repository.PlayerRepository;
import wad.spring.repository.TeamRepository;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Player> listAll() {
        return playerRepository.findAll();
    }
    
    @Override
    @Transactional
    public void saveOrUpdate(Player player, Long teamId) {
        Team t = teamRepository.findOne(teamId);
        t.addPlayer(player);
        teamRepository.save(t);
    }
}
