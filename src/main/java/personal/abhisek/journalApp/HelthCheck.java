package personal.abhisek.journalApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/check")
public class HelthCheck {

    @GetMapping(path = "/")
    public String check() {
        return "Application works good.....";
    }
}
