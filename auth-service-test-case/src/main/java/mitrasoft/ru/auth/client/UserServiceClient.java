package mitrasoft.ru.auth.client;

import lombok.RequiredArgsConstructor;
import mitrasoft.ru.auth.exception.http.ResourceNotFoundException;
import mitrasoft.ru.auth.model.dto.users.UserCredentialsDto;
import mitrasoft.ru.auth.model.enums.QueryType;
import mitrasoft.ru.auth.model.enums.ResourceType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserServiceClient {

    private final WebClient userServiceWebClient;

    public Mono<UserCredentialsDto> findUserByUsername(@PathVariable String username) {
        return userServiceWebClient.get()
                .uri("/credentials/" + username)
                .retrieve()
                .onStatus(
                        status -> status.equals(HttpStatus.NOT_FOUND),
                        response -> Mono.error(
                                new ResourceNotFoundException(ResourceType.USER, QueryType.USERNAME, username)
                        )
                )
                .bodyToMono(UserCredentialsDto.class);
    }
}
