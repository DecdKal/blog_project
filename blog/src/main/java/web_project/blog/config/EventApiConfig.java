package web_project.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "event.api")
public class EventApiConfig {

    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public EventApiConfig setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }
}
