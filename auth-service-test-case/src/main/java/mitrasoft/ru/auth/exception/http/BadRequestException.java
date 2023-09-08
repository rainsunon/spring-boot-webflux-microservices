package mitrasoft.ru.auth.exception.http;


import mitrasoft.ru.auth.exception.AuthServiceException;

public class BadRequestException extends AuthServiceException {
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
