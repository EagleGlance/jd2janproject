package com.noirix.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebMVC implements WebMvcConfigurer {

    private final List<Converter<?, ?>> converters;

    @Override
    public void addFormatters(FormatterRegistry registry) {

        for (Converter<?, ?> converter : converters) {
            registry.addConverter(converter);
        }
    }
}
