package mitrasoft.ru.user.model.enums;

import java.util.Arrays;

public enum Role {
    ROLE_USER,
    ROLE_ADMIN;

    public static boolean isRole(String name) {
        return Arrays.stream(Role.values()).anyMatch(role -> role.name().equals(name));
    }
}
