package fh.thesis.thesistwo;

import fh.thesis.thesistwo.config.ApplicationMetadata;
import fh.thesis.thesistwo.config.ServiceProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ServiceProperty.class, ApplicationMetadata.class})
public class ThesisTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThesisTwoApplication.class, args);
    }

}
