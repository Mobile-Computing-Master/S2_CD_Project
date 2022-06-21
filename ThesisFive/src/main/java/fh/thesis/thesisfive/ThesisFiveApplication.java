package fh.thesis.thesisfive;

import fh.thesis.thesisfive.config.ApplicationMetadata;
import fh.thesis.thesisfive.config.ServiceProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ServiceProperty.class, ApplicationMetadata.class})
public class ThesisFiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThesisFiveApplication.class, args);
    }

}
