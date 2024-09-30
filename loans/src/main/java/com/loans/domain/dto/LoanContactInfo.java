package com.loans.domain.dto;

import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "loans")
@Data
public class LoanContactInfo {

    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSuport;
}
