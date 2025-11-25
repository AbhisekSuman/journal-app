package personal.abhisek.journalApp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personal.abhisek.journalApp.entity.User;
import personal.abhisek.journalApp.service.UserService;
import personal.abhisek.journalApp.utils.JwtUtil;

@RestController
@RequestMapping("/")
@Slf4j
public class PublicController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("signup")
    public void createUser(@RequestBody User user) {
        service.saveUser(user);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            User userDetails = service.getUserByUserName(user.getUserName());
            String jwts = jwtUtil.generateToken(userDetails.getUserName());
            return new ResponseEntity<>(jwts, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error when login: " + e);
            return new ResponseEntity<>("Error"+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }
}
