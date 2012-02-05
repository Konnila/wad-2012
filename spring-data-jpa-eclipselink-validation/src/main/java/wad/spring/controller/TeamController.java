package wad.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import wad.spring.domain.Team;
import wad.spring.service.TeamService;

@Controller
public class TeamController {

    @Autowired
    private TeamService teamService;

    @RequestMapping(value = "/team")
    public String add(@ModelAttribute Team team) {
        teamService.create(team);
        return "redirect:/home";
    }
}
