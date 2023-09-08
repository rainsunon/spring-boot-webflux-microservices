package mitrasoft.ru.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mitrasoft.ru.user.model.dto.users.CreateUserDto;
import mitrasoft.ru.user.model.dto.users.UpdateUserInfoDto;
import mitrasoft.ru.user.model.dto.users.UpdateUserRoleDto;
import mitrasoft.ru.user.model.dto.users.UserDto;
import mitrasoft.ru.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @Operation(method = "Get all users", description = "Retrieve a list of users")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
                    @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Accessing the resource you were trying to reach is forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The resource you were trying to reach is not found"
                    )
            }
    )
    public Flux<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @Operation(method = "Get user by id", description = "Retrieve a user")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
                    @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Accessing the resource you were trying to reach is forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The resource you were trying to reach is not found"
                    )
            }
    )
    public Mono<UserDto> findUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(method = "Create new user", description = "User creating available only for admin")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved new user"),
                    @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Accessing the resource you were trying to reach is forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The resource you were trying to reach is not found"
                    )
            }
    )
    public Mono<UserDto> createUser(@Valid @RequestBody CreateUserDto dto) {
        return userService.createUser(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(method = "Update user info", description = "User info updating available only for admin")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved updated user"),
                    @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Accessing the resource you were trying to reach is forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The resource you were trying to reach is not found"
                    )
            }
    )
    public Mono<UserDto> updateUserInfo(@PathVariable long id, @Valid @RequestBody UpdateUserInfoDto dto) {
        return userService.updateUserInfo(id, dto);
    }

    @PutMapping("/{id}/role")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(method = "Update user role", description = "User role updating available only for admin")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved updated user"),
                    @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Accessing the resource you were trying to reach is forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The resource you were trying to reach is not found"
                    )
            }
    )
    public Mono<UserDto> updateUserRole(@PathVariable long id, @Valid @RequestBody UpdateUserRoleDto dto) {
        return userService.updateUserRole(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(method = "Delete user", description = "User deleting available only for admin")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully deleted user"),
                    @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Accessing the resource you were trying to reach is forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The resource you were trying to reach is not found"
                    )
            }
    )
    public Mono<Void> deleteUserById(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }

}
