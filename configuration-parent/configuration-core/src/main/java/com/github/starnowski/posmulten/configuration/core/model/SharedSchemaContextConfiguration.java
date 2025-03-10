/**
 *     Posmulten library is an open-source project for the generation
 *     of SQL DDL statements that make it easy for implementation of
 *     Shared Schema Multi-tenancy strategy via the Row Security
 *     Policies in the Postgres database.
 *
 *     Copyright (C) 2020  Szymon Tarnowski
 *
 *     This library is free software; you can redistribute it and/or
 *     modify it under the terms of the GNU Lesser General Public
 *     License as published by the Free Software Foundation; either
 *     version 2.1 of the License, or (at your option) any later version.
 *
 *     This library is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *     Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public
 *     License along with this library; if not, write to the Free Software
 *     Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
 *     USA
 */
package com.github.starnowski.posmulten.configuration.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SharedSchemaContextConfiguration {

    private String defaultSchema;
    private String currentTenantIdPropertyType;
    private String currentTenantIdProperty;
    private String getCurrentTenantIdFunctionName;
    private String setCurrentTenantIdFunctionName;
    private String equalsCurrentTenantIdentifierFunctionName;
    private String tenantHasAuthoritiesFunctionName;
    private Boolean forceRowLevelSecurityForTableOwner;
    private String defaultTenantIdColumn;
    private String grantee;
    private Boolean currentTenantIdentifierAsDefaultValueForTenantColumnInAllTables;
    private ValidTenantValueConstraintConfiguration validTenantValueConstraint;
    private List<TableEntry> tables;
    private SqlDefinitionsValidation sqlDefinitionsValidation;
    private List<CustomDefinitionEntry> customDefinitions;
}
