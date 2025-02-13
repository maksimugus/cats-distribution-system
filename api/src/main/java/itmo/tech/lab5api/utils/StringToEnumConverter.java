package itmo.tech.lab5api.utils;

import itmo.tech.lab5domain.cat.Color;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, Color> {

    @Override
    public Color convert(String source) {
        try {
            return Color.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid color: " + source);
        }
    }
}
