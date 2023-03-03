package br.com.erudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class Startup {

    public static void main(String[] args) {
        SpringApplication.run(Startup.class, args);
    }

    @Bean
    CommandLineRunner runner(/*PersonRepository repository,*/ MongoTemplate mongoTemplate) {
        return args -> {
            System.out.println("<----RUNNER---->");
        };
    }
}
