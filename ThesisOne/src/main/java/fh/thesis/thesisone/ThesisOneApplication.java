package fh.thesis.thesisone;

import fh.thesis.thesisone.config.ApplicationMetadata;
import fh.thesis.thesisone.config.ServiceProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ServiceProperty.class, ApplicationMetadata.class})
public class ThesisOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThesisOneApplication.class, args);
    }

}
