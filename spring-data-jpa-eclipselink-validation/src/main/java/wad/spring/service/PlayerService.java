
package wad.spring.service;

import java.util.List;
import wad.spring.domain.Player;

public interface PlayerService {
    public void saveOrUpdate(Player player, Long teamId);
    public List<Player> listAll();
}
