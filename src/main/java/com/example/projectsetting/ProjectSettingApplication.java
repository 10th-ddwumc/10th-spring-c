package com.example.projectsetting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProjectSettingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectSettingApplication.class, args);
    }

}
