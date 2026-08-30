package com.health.vax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point of the Spring Boot application.
 *
 * @SpringBootApplication is a shortcut for three annotations:
 *   - @SpringBootConfiguration : marks this class as a configuration source
 *   - @EnableAutoConfiguration : lets Spring Boot configure beans automatically
 *   - @ComponentScan           : scans this package (com.health.vax) and all
 *                                sub-packages for Spring components such as
 *                                controllers, repositories and services.
 *
 * The component scan starts at this class's package, so all controllers and
 * repositories under com.health.vax are discovered and managed automatically.
 */
@SpringBootApplication
public class VaxApplication {
    public static void main(String[] args) {
        // Bootstraps the application: auto-configuration runs, the embedded
        // Tomcat web server starts on port 8080 and the database connection
        // (see application.properties) is set up.
        SpringApplication.run(VaxApplication.class, args);
    }
}
