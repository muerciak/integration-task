import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@SpringBootApplication
@ComponentScan("task1.conf")
@Configuration
@EnableConfigurationProperties
public class Application {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
    }

}
