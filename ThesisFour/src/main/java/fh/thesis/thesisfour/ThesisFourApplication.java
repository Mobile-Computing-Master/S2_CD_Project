package fh.thesis.thesisfour;

import fh.thesis.thesisfour.config.ApplicationMetadata;
import fh.thesis.thesisfour.config.ServiceProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ServiceProperty.class, ApplicationMetadata.class})
public class ThesisFourApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThesisFourApplication.class, args);
    }

}
