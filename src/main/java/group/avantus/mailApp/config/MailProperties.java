package group.avantus.mailApp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.mail")
@Getter
@Setter
public class MailProperties {

    private String host;
    private String username;
    private String password;
    private int port;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private Boolean auth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private Boolean tls;
    @Value("${spring.mail.properties.mail.transport.protocol}")
    private String protocol;
}
