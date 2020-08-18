package com.github.starnowski.posmulten.postgresql.core.context;

import com.github.starnowski.posmulten.postgresql.core.rls.TenantHasAuthoritiesFunctionInvocationFactory;

public class TableRLSPolicySQLDefinitionsProducerParameters implements AbstractTableRLSPolicySQLDefinitionsProducerParameters{

    private final String grantee;
    private final TableKey tableKey;
    private final String policyName;
    private final TenantHasAuthoritiesFunctionInvocationFactory tenantHasAuthoritiesFunctionInvocationFactory;

    public TableRLSPolicySQLDefinitionsProducerParameters(String grantee, TableKey tableKey, String policyName, TenantHasAuthoritiesFunctionInvocationFactory tenantHasAuthoritiesFunctionInvocationFactory) {
        this.grantee = grantee;
        this.tableKey = tableKey;
        this.policyName = policyName;
        this.tenantHasAuthoritiesFunctionInvocationFactory = tenantHasAuthoritiesFunctionInvocationFactory;
    }

    public String getGrantee() {
        return grantee;
    }

    public TableKey getTableKey() {
        return tableKey;
    }

    public String getPolicyName() {
        return policyName;
    }

    public TenantHasAuthoritiesFunctionInvocationFactory getTenantHasAuthoritiesFunctionInvocationFactory() {
        return tenantHasAuthoritiesFunctionInvocationFactory;
    }

    public static class TableRLSPolicySQLDefinitionsProducerParametersBuilder
    {
        private String grantee;
        private TableKey tableKey;
        private String policyName;
        private TenantHasAuthoritiesFunctionInvocationFactory tenantHasAuthoritiesFunctionInvocationFactory;

        public TableRLSPolicySQLDefinitionsProducerParametersBuilder withGrantee(String grantee) {
            this.grantee = grantee;
            return this;
        }

        public TableRLSPolicySQLDefinitionsProducerParametersBuilder withTableKey(TableKey tableKey) {
            this.tableKey = tableKey;
            return this;
        }

        public TableRLSPolicySQLDefinitionsProducerParametersBuilder withPolicyName(String policyName) {
            this.policyName = policyName;
            return this;
        }

        public TableRLSPolicySQLDefinitionsProducerParametersBuilder withTenantHasAuthoritiesFunctionInvocationFactory(TenantHasAuthoritiesFunctionInvocationFactory tenantHasAuthoritiesFunctionInvocationFactory) {
            this.tenantHasAuthoritiesFunctionInvocationFactory = tenantHasAuthoritiesFunctionInvocationFactory;
            return this;
        }

        public TableRLSPolicySQLDefinitionsProducerParameters build()
        {
            return new TableRLSPolicySQLDefinitionsProducerParameters(grantee, tableKey, policyName, tenantHasAuthoritiesFunctionInvocationFactory);
        }
    }
}
