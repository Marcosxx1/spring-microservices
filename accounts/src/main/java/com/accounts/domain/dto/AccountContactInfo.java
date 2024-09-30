package com.accounts.domain.dto;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
public class AccountContactInfo {
    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSuport;
}
