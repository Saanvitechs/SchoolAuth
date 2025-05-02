package com.erp.school.tenant;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "tenants")
public class DatabaseConfigurations {
    private Map<String,DbConfiguration> configurations = new HashMap<String,DbConfiguration>();
    

    public Map<String, DbConfiguration> getConfigurations() {
		return configurations;
	}

	public void setConfigurations(Map<String, DbConfiguration> configurations) {
		this.configurations = configurations;
	}


	public Map<Object, Object> createTargetDataSources() {
        Map<Object, Object> result = new HashMap<>();
        configurations.forEach((key, value) ->  result.put(key, value.createDataSource()));
        return result;
    }

}
