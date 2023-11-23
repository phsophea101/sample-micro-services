package com.example.gateway.common.config;

import com.example.gateway.common.DefaultSerializerProvider;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.DecimalFormat;

@Configuration
public class ObjectMapperConfiguration {
    @Bean
    public DecimalFormat decimalFormat() {
        return new DecimalFormat("#.00");
    }

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder mapperBuilder) {
        ObjectMapper mapper = mapperBuilder.build();
        mapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());
        mapper.setSerializerProvider(new DefaultSerializerProvider());
//        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

}
