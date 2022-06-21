package fh.thesis.thesisthree;

import fh.thesis.thesisthree.config.ApplicationMetadata;
import fh.thesis.thesisthree.config.ServiceProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ServiceProperty.class, ApplicationMetadata.class})
public class ThesisThreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThesisThreeApplication.class, args);
    }

}
