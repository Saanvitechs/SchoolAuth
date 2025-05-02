package com.erp.school.tenant;

public enum DataSourceConstants {
    TENANT_ID("tenant"),
    NETWELL("netwell"),
    UHF("uhf"),
    INNOVATION("innovation"),
    IP_AEC("aec"),
    ICHRA("ichra");

    private final String value;

    private DataSourceConstants(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}