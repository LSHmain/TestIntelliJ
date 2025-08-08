package com.shinhan.sbproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {
                "com.shinhan.sbproject",
                "net.firstzone.myapp",
                "com.test.chat"
        }
)
@EnableJpaRepositories(basePackages = {
        "com.shinhan.sbproject.repository",
        "com.test.chat" // ChatRoomRepository 포함
})
@EntityScan(basePackages = {
        "com.shinhan.sbproject.entity",
        "com.test.chat" // ChatRoom 엔티티 포함
})
@EnableJpaAuditing
public class SbprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbprojectApplication.class, args);
    }

}
