package wad.spring.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.spring.domain.Player;
import wad.spring.service.PlayerService;
import wad.spring.service.TeamService;

@Controller
public class PlayerController {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private TeamService teamService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("players", playerService.listAll());
        return "list";
    }

    @RequestMapping(value = "/player", method = RequestMethod.GET)
    public String getPlayer(Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("teams", teamService.list());
        return "player";
    }

    @RequestMapping(value = "/player", method = RequestMethod.POST)
    public String addPlayer(@Valid @ModelAttribute("player") Player player, BindingResult result,
            @RequestParam Long teamId, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("teams", teamService.list());
            return "player";
        }

        playerService.saveOrUpdate(player, teamId);
        return "redirect:/home";
    }
}