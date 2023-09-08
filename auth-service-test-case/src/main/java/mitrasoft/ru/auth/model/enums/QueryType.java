package mitrasoft.ru.auth.model.enums;

import lombok.Getter;

@Getter
public enum QueryType {

    USERNAME("emails"),
    ;

    private final String queryName;
    QueryType(String queryName) {
        this.queryName = queryName;
    }
}
