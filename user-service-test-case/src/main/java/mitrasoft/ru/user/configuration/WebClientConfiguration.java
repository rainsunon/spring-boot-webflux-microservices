package mitrasoft.ru.user.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Value("${clients.auth-service}")
    private String authService;

    @Bean
    public WebClient authServiceWebClient() {
        return WebClient.builder()
                .baseUrl(authService + "/api/v1/auth")
                .build();
    }
}
