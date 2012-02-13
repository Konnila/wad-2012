package wad.spring.controller;

import java.util.Random;
import javax.jms.JMSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import wad.spring.jms.JmsReader;
import wad.spring.jms.JmsSender;

@Controller
public class HomeController {

    @Autowired
    JmsReader reader;
    @Autowired
    JmsSender sender;

    @RequestMapping(value = "/home")
    public String home(Model model) throws JMSException {
        sender.send("The message " + new Random().nextInt(500) + "!");
        model.addAttribute("msg", reader.read());

        return "home";
    }
}
