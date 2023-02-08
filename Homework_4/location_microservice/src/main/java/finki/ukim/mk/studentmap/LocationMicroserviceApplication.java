package finki.ukim.mk.studentmap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

//TODO add eureka client
@SpringBootApplication
public class LocationMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LocationMicroserviceApplication.class,args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
