package group.avantus.mailApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;

@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@TestConfiguration
class EmailApplicationTests {

    @Test
    void contextLoads() {
    }

}
