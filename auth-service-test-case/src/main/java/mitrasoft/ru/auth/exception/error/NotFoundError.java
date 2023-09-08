package mitrasoft.ru.auth.exception.error;

import lombok.Getter;
import mitrasoft.ru.auth.model.enums.QueryType;
import mitrasoft.ru.auth.model.enums.ResourceType;

import java.util.Collection;

/**
 * Ошибка отсутствия ресурса.
 *
 * @param <K> тип идентификатора
 */
@Getter
public class NotFoundError<K> extends SimpleApiError {

    private final ResourceType resourceType;
    private final QueryType queryType;
    private final Collection<K> ids;

    public NotFoundError(ResourceType resourceType, QueryType queryType, Collection<K> identifiers, String message) {
        super(message);
        this.resourceType = resourceType;
        this.queryType = queryType;
        this.ids = identifiers;
    }
}
