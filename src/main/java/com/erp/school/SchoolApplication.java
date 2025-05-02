package com.erp.school;

import com.erp.school.tenant.DatabaseConfigurations;
import com.erp.school.tenant.TenantRoutingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@EnableConfigurationProperties(DatabaseConfigurations.class)
@SpringBootApplication
public class SchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

	@Autowired
	private DatabaseConfigurations databaseConfigurations;

	@Bean
	public DataSource dataSource() {
		TenantRoutingDataSource dataSource= new TenantRoutingDataSource();
		dataSource.setTargetDataSources(databaseConfigurations.createTargetDataSources());
		return dataSource;
	}


}
