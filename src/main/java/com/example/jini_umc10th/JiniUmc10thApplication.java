package com.example.jini_umc10th;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  // ← 이게 있어야 @CreatedDate 동작
public class JiniUmc10thApplication {

    public static void main(String[] args) {
        SpringApplication.run(JiniUmc10thApplication.class, args);
    }

}
