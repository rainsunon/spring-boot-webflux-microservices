package mitrasoft.ru.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mitrasoft.ru.user.model.dto.auth.UserCredentialsDto;
import mitrasoft.ru.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    @GetMapping("/credentials/{username}")
    @Operation(method = "Get user credential", description = "Retrieve a  user credential for generating token")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved user credential"),
                    @ApiResponse(responseCode = "404", description = "User wit username not found")
            }
    )
    public Mono<UserCredentialsDto> findUserByUsername(@PathVariable String username) {
        return userService.getUserCredentialsByEmail(username);
    }
}
