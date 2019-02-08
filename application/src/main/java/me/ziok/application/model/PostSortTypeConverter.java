package me.ziok.application.model;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostSortTypeConverter implements Converter<String, PostSortType> {
    public PostSortType convert(String value){
        return PostSortType.value(Integer.parseInt(value));
    }
}