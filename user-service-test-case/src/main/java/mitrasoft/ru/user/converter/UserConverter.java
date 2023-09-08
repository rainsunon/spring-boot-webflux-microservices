package mitrasoft.ru.user.converter;

import mitrasoft.ru.user.model.dto.users.UserDto;
import mitrasoft.ru.user.model.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UserConverter {

    private UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    public Mono<UserDto> toDto(Mono<User> userMono) {
        return userMono.map(this::toDto);
    }

    public Flux<UserDto> toDto(Flux<User> usersFlux) {
        return usersFlux.map(this::toDto);
    }
}
