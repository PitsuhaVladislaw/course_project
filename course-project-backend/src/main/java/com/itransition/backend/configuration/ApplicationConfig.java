package com.itransition.backend.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.TimeZone;

@Configuration
@EnableScheduling
public class ApplicationConfig implements WebMvcConfigurer {

    @Value("${backend.host}")
    private String backendHost;

    @Value("${frontend.host}")
    private String frontendHost;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(backendHost, frontendHost)
                .allowedHeaders(CorsConfiguration.ALL).allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(),
                        HttpMethod.PUT.name(), HttpMethod.DELETE.name()).maxAge(1800L);
    }

    @Bean
    @Autowired
    public LocalValidatorFactoryBean localValidatorFactoryBean(MessageSource messageSource) {
        var localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource);
        return localValidatorFactoryBean;
    }

    @Autowired
    public void setTimeZone(ObjectMapper objectMapper) {
        objectMapper.setTimeZone(TimeZone.getDefault());
    }

}
