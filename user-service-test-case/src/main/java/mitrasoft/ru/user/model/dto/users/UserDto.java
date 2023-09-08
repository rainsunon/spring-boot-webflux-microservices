package mitrasoft.ru.user.model.dto.users;

import lombok.Data;
import mitrasoft.ru.user.model.enums.Role;

import java.time.LocalDate;

@Data
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate birthdayAt;

    private Role role;
}
