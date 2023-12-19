package com.iksadnorth.rmsoft.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("security.admin")
public class SecurityAdminProperties {
    private String key;
}
