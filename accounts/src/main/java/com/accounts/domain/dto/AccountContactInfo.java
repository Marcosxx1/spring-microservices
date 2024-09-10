package com.accounts.domain.dto;

import java.util.List;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;

/*Record:
 * All fields are final
 * There will be a constructor and Getter methods
 * NO SETTER METHODS
 * Can only initialize data only ONCE*/
@ConfigurationProperties(prefix = "accounts")
public record AccountContactInfo(String message, Map<String, String> contactDetails, List<String> onCallSuport) {}
