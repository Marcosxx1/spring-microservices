package com.accounts.domain.dto;

import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "accounts")
@Data
public class AccountContactInfo {
    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSuport;
}
