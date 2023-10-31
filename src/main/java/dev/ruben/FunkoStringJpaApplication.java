package dev.ruben;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("dev.ruben.models")
public class FunkoStringJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunkoStringJpaApplication.class, args);
    }
}
