package personal.abhisek.journalApp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import personal.abhisek.journalApp.entity.User;
import personal.abhisek.journalApp.repository.UserRepository;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


public class MyUserDetailsServiceTest {

    @InjectMocks
    MyUserDetailService myUserDetailService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsername() {
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Abhi").password("vvghj").roles(new ArrayList<>()).build());
        UserDetails user = myUserDetailService.loadUserByUsername("Abhi");
        Assertions.assertNotNull(user);
    }
}
