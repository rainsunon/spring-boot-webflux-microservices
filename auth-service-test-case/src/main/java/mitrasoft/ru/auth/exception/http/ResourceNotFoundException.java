package mitrasoft.ru.auth.exception.http;

import lombok.Getter;
import mitrasoft.ru.auth.exception.AuthServiceException;
import mitrasoft.ru.auth.model.enums.QueryType;
import mitrasoft.ru.auth.model.enums.ResourceType;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Ресурс не найден.
 */
@Getter
public class ResourceNotFoundException extends AuthServiceException {

    private final ResourceType type;
    private final QueryType queryType;
    private final Collection<String> values;

    public ResourceNotFoundException(ResourceType type, QueryType queryType, Object value) {
        this(type, queryType, List.of(value.toString()));
    }

    public ResourceNotFoundException(ResourceType type, QueryType queryType, String value) {
        this(type, queryType, List.of(value));
    }

    public ResourceNotFoundException(ResourceType type, QueryType queryType, Collection<Object> values) {
        this.type = type;
        this.queryType = queryType;
        this.values = values.stream().map(Object::toString).collect(Collectors.toList());
    }

    @Override
    public String getMessage() {
        return String.format("Failed to find [%s] with %s [%s]", type, queryType.getQueryName(), values);
    }

}
