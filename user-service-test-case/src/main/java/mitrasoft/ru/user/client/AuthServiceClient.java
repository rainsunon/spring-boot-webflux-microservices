package mitrasoft.ru.user.client;

import lombok.RequiredArgsConstructor;
import mitrasoft.ru.user.exception.http.BadRequestException;
import mitrasoft.ru.user.exception.http.ResourceNotFoundException;
import mitrasoft.ru.user.model.dto.auth.TokenInfoDto;
import mitrasoft.ru.user.model.dto.auth.ValidateTokenDto;
import mitrasoft.ru.user.model.enums.ResourceType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthServiceClient {

    private final WebClient authServiceWebClient;

    public Mono<TokenInfoDto> validateToken(ValidateTokenDto validateTokenDto) {
        return authServiceWebClient.post()
                .uri("/validate")
                .body(Mono.just(validateTokenDto), ValidateTokenDto.class)
                .retrieve()
                .onStatus(
                        status -> status.equals(HttpStatus.BAD_REQUEST),
                        response -> Mono.error(
                                new BadRequestException("Not valid token")
                        )
                )
                .bodyToMono(TokenInfoDto.class);
    }
}
