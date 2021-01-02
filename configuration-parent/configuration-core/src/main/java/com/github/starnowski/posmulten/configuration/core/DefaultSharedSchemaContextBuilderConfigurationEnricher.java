package com.github.starnowski.posmulten.configuration.core;

import com.github.starnowski.posmulten.configuration.core.model.SharedSchemaContextConfiguration;
import com.github.starnowski.posmulten.postgresql.core.context.DefaultSharedSchemaContextBuilder;

public class DefaultSharedSchemaContextBuilderConfigurationEnricher {

    public DefaultSharedSchemaContextBuilder enrich(DefaultSharedSchemaContextBuilder builder, SharedSchemaContextConfiguration contextConfiguration) {
        //TODO
        if (contextConfiguration.getCurrentTenantIdPropertyType() != null) {
            builder.setCurrentTenantIdPropertyType(contextConfiguration.getCurrentTenantIdPropertyType());
        }
        builder.setCurrentTenantIdProperty(contextConfiguration.getCurrentTenantIdProperty());
        builder.setGetCurrentTenantIdFunctionName(contextConfiguration.getGetCurrentTenantIdFunctionName());
        builder.setSetCurrentTenantIdFunctionName(contextConfiguration.getSetCurrentTenantIdFunctionName());
        builder.setEqualsCurrentTenantIdentifierFunctionName(contextConfiguration.getEqualsCurrentTenantIdentifierFunctionName());
        builder.setTenantHasAuthoritiesFunctionName(contextConfiguration.getTenantHasAuthoritiesFunctionName());
        builder.setForceRowLevelSecurityForTableOwner(contextConfiguration.getForceRowLevelSecurityForTableOwner());
        builder.setDefaultTenantIdColumn(contextConfiguration.getDefaultTenantIdColumn());
        builder.setGrantee(contextConfiguration.getGrantee());
        builder.setCurrentTenantIdentifierAsDefaultValueForTenantColumnInAllTables(contextConfiguration.getCurrentTenantIdentifierAsDefaultValueForTenantColumnInAllTables());
        return builder;
    }
}
