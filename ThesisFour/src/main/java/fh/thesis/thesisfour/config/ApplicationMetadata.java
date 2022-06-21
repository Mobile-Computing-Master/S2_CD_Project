package fh.thesis.thesisfour.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.application")
public record ApplicationMetadata(String name) {

}
