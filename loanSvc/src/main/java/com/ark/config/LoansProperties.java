package com.ark.config;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class LoansProperties {
	private String msg;
	private String buildVersion;
	private Map<String, String> mailDetails;
	private List<String> activeBranches;

	public LoansProperties(String msg, String buildVersion, Map<String, String> mailDetails,
			List<String> activeBranches) {
		super();
		this.msg = msg;
		this.buildVersion = buildVersion;
		this.mailDetails = mailDetails;
		this.activeBranches = activeBranches;
	}

}
