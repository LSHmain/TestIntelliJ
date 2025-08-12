package com.shinhan.sbproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {
                "com.shinhan.sbproject",
                "net.firstzone.myapp",

        }
)
@EnableJpaRepositories(basePackages = {
        "com.shinhan.sbproject.repository",
        "com.shinhan.sbproject.repository2",

})
@EntityScan(basePackages = {
        "com.shinhan.sbproject.entity",
        "com.shinhan.sbproject.entity2",

})
@EnableJpaAuditing
public class SbprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbprojectApplication.class, args);
    }

}
