package mitrasoft.ru.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mitrasoft.ru.user.converter.UserConverter;
import mitrasoft.ru.user.exception.http.BadRequestException;
import mitrasoft.ru.user.exception.http.ResourceNotFoundException;
import mitrasoft.ru.user.model.dto.auth.UserCredentialsDto;
import mitrasoft.ru.user.model.dto.users.CreateUserDto;
import mitrasoft.ru.user.model.dto.users.UpdateUserInfoDto;
import mitrasoft.ru.user.model.dto.users.UpdateUserRoleDto;
import mitrasoft.ru.user.model.dto.users.UserDto;
import mitrasoft.ru.user.model.entity.User;
import mitrasoft.ru.user.model.enums.QueryType;
import mitrasoft.ru.user.model.enums.ResourceType;
import mitrasoft.ru.user.model.enums.Role;
import mitrasoft.ru.user.repository.UserRepository;
import mitrasoft.ru.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Flux<UserDto> findAll() {
        return userConverter.toDto(
                userRepository.findAll()
        );
    }

    private Mono<User> findUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<UserDto> getUserById(long id) {
        return userConverter.toDto(
                findUserById(id)
                        .switchIfEmpty(
                                Mono.error(new ResourceNotFoundException(ResourceType.USER, QueryType.ID, id))
                        )
        );
    }

    @Override
    public Mono<UserCredentialsDto> getUserCredentialsByEmail(String email) {
        return userRepository.findByEmail(email)
                .switchIfEmpty(
                        Mono.error(new ResourceNotFoundException(ResourceType.USER, QueryType.EMAIL, email))
                ).map(user -> new UserCredentialsDto(user.getPassword(), user.getRole().name()));
    }

    @Override
    public Mono<UserDto> createUser(CreateUserDto dto) {
        return userRepository.existsByEmail(dto.getEmail())
                .flatMap(isExist -> {
                    if (isExist) {
                        return Mono.error(
                                new BadRequestException("User with email [" + dto.getEmail() + "] already exists")
                        );
                    } else {
                        User user = new User();
                        user.setPassword(passwordEncoder.encode(dto.getPassword()));
                        BeanUtils.copyProperties(dto, user, "password");
                        return userConverter.toDto(
                                userRepository.save(user)
                        );
                    }
                });
    }

    @Override
    public Mono<UserDto> updateUserInfo(long id, UpdateUserInfoDto dto) {
        return findUserById(id)
                .switchIfEmpty(
                        Mono.error(new ResourceNotFoundException(ResourceType.USER, QueryType.ID, id))
                )
                .flatMap(dbUser -> {
                    BeanUtils.copyProperties(dto, dbUser, "password");
                    return userConverter.toDto(
                            userRepository.save(dbUser)
                    );
                });
    }

    @Override
    public Mono<UserDto> updateUserRole(long id, UpdateUserRoleDto dto) {
        return findUserById(id)
                .switchIfEmpty(
                        Mono.error(new ResourceNotFoundException(ResourceType.USER, QueryType.ID, id))
                )
                .flatMap(dbUser -> {
                    String role = dto.getRole();
                    if (Role.isRole(role)) {
                        dbUser.setRole(Role.valueOf(role));
                        return userConverter.toDto(
                                userRepository.save(dbUser)
                        );
                    } else {
                        return Mono.error(new BadRequestException("Role with name " + role + " not exists"));
                    }
                });
    }

    @Override
    public Mono<Void> deleteUserById(Long id) {
        return userRepository.deleteById(id);
    }
}
