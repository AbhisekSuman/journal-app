package personal.abhisek.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import personal.abhisek.journalApp.entity.User;
import personal.abhisek.journalApp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/")
    public List<User> getAllUser() {
        return service.getAllUser();
    }



    @PutMapping("")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user1 = service.getUserByUserName(userName);

        if (user1 != null) {
            user1.setUserName(user.getUserName());
            user1.setPassword(user.getPassword());
            service.saveUser(user1);
            return new ResponseEntity<>(user1, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/username/{userName}")
    public ResponseEntity<?> deleteUser(@PathVariable String userName, @RequestBody User user) {
        User user1 = service.getUserByUserName(userName);

        if (user1 != null) {
            user1.setUserName(user.getUserName());
            user1.setPassword(user.getPassword());
            service.saveUser(user1);
            return new ResponseEntity<>(user1, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
