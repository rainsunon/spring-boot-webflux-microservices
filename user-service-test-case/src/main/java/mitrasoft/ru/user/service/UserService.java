package mitrasoft.ru.user.service;

import mitrasoft.ru.user.model.dto.auth.UserCredentialsDto;
import mitrasoft.ru.user.model.dto.users.CreateUserDto;
import mitrasoft.ru.user.model.dto.users.UpdateUserInfoDto;
import mitrasoft.ru.user.model.dto.users.UpdateUserRoleDto;
import mitrasoft.ru.user.model.dto.users.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Flux<UserDto> findAll();

    Mono<UserDto> getUserById(long id);

    Mono<UserCredentialsDto> getUserCredentialsByEmail(String email);

    Mono<UserDto> createUser(CreateUserDto dto);

    Mono<UserDto> updateUserInfo(long id, UpdateUserInfoDto dto);

    Mono<UserDto> updateUserRole(long id, UpdateUserRoleDto dto);

    Mono<Void> deleteUserById(Long id);
}
