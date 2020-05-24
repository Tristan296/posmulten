package com.github.starnowski.posmulten.postgresql.core.rls;

public interface RLSPolicyProducerParameters {

    String getPolicyName();

    String getPolicyTable();

    String getPolicySchema();

    String getGrantee(); //TODO required

    String getTenantIdColumn(); //TODO not blank

    PermissionCommandPolicyEnum getPermissionCommandPolicy(); //TODO required

    TenantHasAuthoritiesFunctionInvocationFactory getWithCheckExpressionTenantHasAuthoritiesFunctionInvocationFactory(); //TODO required for INSERT

    TenantHasAuthoritiesFunctionInvocationFactory getUsingExpressionTenantHasAuthoritiesFunctionInvocationFactory(); //TODO required for SELECT and DELETE
}
