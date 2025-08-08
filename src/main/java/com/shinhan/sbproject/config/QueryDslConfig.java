package com.shinhan.sbproject.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class QueryDslConfig {

    @Bean
    public JPAQueryFactory f1(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}
