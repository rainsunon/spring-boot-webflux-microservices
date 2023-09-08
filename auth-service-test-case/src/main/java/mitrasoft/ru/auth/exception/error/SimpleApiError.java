package mitrasoft.ru.auth.exception.error;

import lombok.*;

/**
 * Базовый класс для ошибок в API методах.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class SimpleApiError implements ApiError {

    /**
     * Текстовое сообщение об ошибке.
     */
    private String message;
}
