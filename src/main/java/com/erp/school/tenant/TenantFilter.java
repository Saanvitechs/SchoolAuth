package com.erp.school.tenant;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TenantFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String tenantId = request.getHeader(DataSourceConstants.TENANT_ID.value());
            if (tenantId != null) {
                TenantContext.setTenant(tenantId);
            }
            filterChain.doFilter(request, response);
        } finally {
            TenantContext.clear(); // always clean up to avoid memory leaks
        }
    }
}
