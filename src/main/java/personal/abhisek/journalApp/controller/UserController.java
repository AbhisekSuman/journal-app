package personal.abhisek.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import personal.abhisek.journalApp.api.response.WeatherReesponse;
import personal.abhisek.journalApp.entity.User;
import personal.abhisek.journalApp.service.UserService;
import personal.abhisek.journalApp.service.WeatherService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    private WeatherService weatherService;


    @GetMapping("/profile")
    public ResponseEntity<?> getUser(@RequestParam String city) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        WeatherReesponse weatherReesponse = weatherService.getWeather(city);
        String greetings = "";

        if (weatherReesponse != null) {
            greetings = "Current weather in Mumbai feels like " + weatherReesponse.getCurrent().getFeelsLikeC() + " celcius";
        }

        return new ResponseEntity<>("Hii, " + userName + greetings,HttpStatus.OK);
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
