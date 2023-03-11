package br.com.erudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@EnableMongoRepositories
@SpringBootApplication
public class Startup {

    public static void main(String[] args) {
        SpringApplication.run(Startup.class, args);
    }

    @Bean
    CommandLineRunner runner(/*PersonRepository repository,*/ MongoTemplate mongoTemplate) {
        return args -> {
            Map<String, PasswordEncoder> encoders = new HashMap<>();
            Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder("", 8, 185000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
            encoders.put("pbkdf2", pbkdf2Encoder);
            DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
            passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);

            var result = passwordEncoder.encode("admin1234");
            System.out.println("<----"+result+"---->");
            System.out.println("<----RUNNER---->");
        };
    }
}
