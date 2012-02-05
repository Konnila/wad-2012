/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import wad.spring.service.PlayerService;
import wad.spring.service.TeamService;

@Controller
public class HomeController {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private TeamService teamService;

    @RequestMapping(value = "/home")
    public String home(Model model) {
        model.addAttribute("players", playerService.listAll());
        model.addAttribute("teams", teamService.list());
        return "list";
    }
}
