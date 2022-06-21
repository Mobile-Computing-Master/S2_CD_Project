package fh.thesis.thesisfour.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "thesis.service")
public record ServiceProperty(String host, int port) {
    public String fullServiceAddress() {
        return "http://" + host + ":" + port;
    }
}
