package personal.abhisek.journalApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import personal.abhisek.journalApp.repository.UserRepositoryImpl;

import java.util.List;

@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    private UserRepositoryImpl repository;

    @Test
    public void testUsers() {
        Assertions.assertNotNull(repository.getUsersForSA());
    }
}
