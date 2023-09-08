package mitrasoft.ru.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mitrasoft.ru.auth.model.dto.auth.TokenInfoDto;
import mitrasoft.ru.auth.model.dto.auth.TokenPayloadDto;
import mitrasoft.ru.auth.model.dto.auth.UserLoginDto;
import mitrasoft.ru.auth.model.dto.auth.ValidateTokenDto;
import mitrasoft.ru.auth.service.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final TokenService tokenService;


    @PostMapping("/token")
    @Operation(method = "Generate new token for user", description = "Retrieve a payload with token and his meta data")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved payload"),
                    @ApiResponse(responseCode = "400", description = "Not correct password"),
                    @ApiResponse(responseCode = "404", description = "User wit username not found")
            }
    )
    public Mono<TokenPayloadDto> generateToken(@RequestBody UserLoginDto userLoginDto) {
        return tokenService.generateToken(userLoginDto);
    }

    @PostMapping("/validate")
    @Operation(method = "Validate token", description = "Retrieve a info about token with action time and user info")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved token info"),
                    @ApiResponse(responseCode = "400", description = "Not valid token")
            }
    )
    public Mono<TokenInfoDto> validateToken(@RequestBody ValidateTokenDto validateTokenDto) {
        return tokenService.validateToken(validateTokenDto);
    }
}
