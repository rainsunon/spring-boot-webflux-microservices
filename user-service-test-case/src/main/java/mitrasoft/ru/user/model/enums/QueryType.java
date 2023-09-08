package mitrasoft.ru.user.model.enums;

import lombok.Getter;

@Getter
public enum QueryType {

    ID("ids"),
    EMAIL("emails"),
    ;

    private final String queryName;

    QueryType(String queryName) {
        this.queryName = queryName;
    }
}
