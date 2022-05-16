/**
 * 
 */
package com.ark.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import lombok.Data;

/**
 * @author Ark
 *
 */
@Configuration
@ConfigurationProperties(prefix = "accounts")
@Data
public class AccountsConfig {
	
	private String msg;
	private String buildVersion;
	private Map<String, String> mailDetails;
	private List<String> activeBranches;
	

}
