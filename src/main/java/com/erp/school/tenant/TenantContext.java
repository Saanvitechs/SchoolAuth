package com.erp.school.tenant;

public class TenantContext {
    private static final ThreadLocal<String> tenantContext = new ThreadLocal<>();

    public static void setTenant(String tenantId) {
        tenantContext.set(tenantId);
    }

    public static String getTenant() {
        return tenantContext.get();
    }

    public static void clear() {
        tenantContext.remove();
    }
}
