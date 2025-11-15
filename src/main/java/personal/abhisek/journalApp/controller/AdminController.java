package personal.abhisek.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personal.abhisek.journalApp.entity.User;
import personal.abhisek.journalApp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService service;

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = service.getAllUser();

        if (users != null && !users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createAdmin(User user) {
        service.saveAdmin(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
