package personal.abhisek.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personal.abhisek.journalApp.entity.User;
import personal.abhisek.journalApp.service.UserService;

@RestController
@RequestMapping("/")
public class PublicController {

    @Autowired
    private UserService service;

    @PostMapping("/signup")
    public void createUser(@RequestBody User user) {
        service.saveUser(user);
    }
}
