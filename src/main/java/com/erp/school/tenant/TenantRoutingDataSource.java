package com.erp.school.tenant;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class TenantRoutingDataSource extends AbstractRoutingDataSource {



	/*
	 * service to identify the tenant
	 */
	@Override
	protected Object determineCurrentLookupKey() {
	String tenant = TenantContext.getTenant();


	if (tenant != null) {
		if (tenant.contains(DataSourceConstants.NETWELL.name()))
			return DataSourceConstants.NETWELL.value();
		else if (tenant.contains(DataSourceConstants.INNOVATION.name()))
			return DataSourceConstants.INNOVATION.value();
		else if (tenant.contains(DataSourceConstants.UHF.name()))
			return DataSourceConstants.UHF.value();
		else if (tenant.contains(DataSourceConstants.IP_AEC.name()))
			return DataSourceConstants.IP_AEC.value();
		else
			return null;

		} else {
			return DataSourceConstants.UHF.value();
		}
	}


}
