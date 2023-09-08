package mitrasoft.ru.user.converter;

import mitrasoft.ru.user.model.enums.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@Component
@ReadingConverter
@WritingConverter
public class RoleConverter implements Converter<String, Role> {
    @Override
    public Role convert(String source) {
        return Role.valueOf(source);
    }
}
