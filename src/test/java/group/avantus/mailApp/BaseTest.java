package group.avantus.mailApp;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;


@ActiveProfiles("test")
@Transactional
public abstract class BaseTest {
}
