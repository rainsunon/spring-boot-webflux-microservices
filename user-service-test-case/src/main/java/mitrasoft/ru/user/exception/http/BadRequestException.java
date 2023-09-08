package mitrasoft.ru.user.exception.http;

import mitrasoft.ru.user.exception.UserServiceException;

public class BadRequestException extends UserServiceException {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
