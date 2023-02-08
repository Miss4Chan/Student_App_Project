package finki.ukim.mk.studentmap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@EnableEurekaClient
@SpringBootApplication
public class HelpMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelpMicroserviceApplication.class, args);
    }
}
