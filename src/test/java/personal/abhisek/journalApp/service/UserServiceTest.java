package personal.abhisek.journalApp.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import personal.abhisek.journalApp.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @BeforeAll
    static void setup() {

    }

    @AfterAll
    static void after() {

    }

    @BeforeEach
    void beforeEach() {

    }

    @AfterEach
    void afterEach() {

    }

    @Test
    @Disabled
    public void testAdd() {
        assertEquals(4, 2 + 2);
    }

    @Test
    @Disabled
    public void testFindByUserName() {
        assertNotNull(userRepository.findByUserName("Abhi"));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1, 2",
            "3, 4, 5"
    })
    @Disabled
    public void test(int a, int b, int res) {
        assertEquals(res, a + b);
    }
}
