package personal.abhisek.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void testMail() {
        emailService.sendMail("wexifiy247@etramay.com", "This is a sample mail", "Hi, Good Afternoon");
    }
}
