package com.github.starnowski.posmulten.configuration.yaml.context

import com.github.starnowski.posmulten.configuration.yaml.AbstractSpecification
import com.github.starnowski.posmulten.configuration.yaml.exceptions.YamlInvalidSchema
import spock.lang.Unroll

import static com.github.starnowski.posmulten.configuration.yaml.TestProperties.ALL_FIELDS_FILE_PATH
import static com.github.starnowski.posmulten.configuration.yaml.TestProperties.INVALID_LIST_NODES_BLANK_FIELDS_PATH
import static com.github.starnowski.posmulten.configuration.yaml.TestProperties.INVALID_NESTED_NODE_BLANK_FIELDS_FILE_PATH
import static com.github.starnowski.posmulten.configuration.yaml.TestProperties.INVALID_NESTED_NODE_EMPTY_LIST_FILE_PATH
import static com.github.starnowski.posmulten.configuration.yaml.TestProperties.INVALID_ROOT_NODE_BLANK_FIELDS_FILE_PATH
import static com.github.starnowski.posmulten.configuration.yaml.TestProperties.ONLY_MANDATORY_FIELDS_FILE_PATH

class YamlConfigurationDefaultSharedSchemaContextBuilderFactoryTest extends AbstractSpecification {

    def tested = new YamlConfigurationDefaultSharedSchemaContextBuilderFactory()

    @Unroll
    def "should create builder component based on file #filePath"()
    {
        given:
            def resolvedPath = resolveFilePath(filePath)

        when:
            def builder = tested.build(resolvedPath)

        then:
            builder

        and: "builder should return a non-empty list of DDL statements"
            !builder.build().getSqlDefinitions().isEmpty()

        where:
            filePath << [ALL_FIELDS_FILE_PATH, ONLY_MANDATORY_FIELDS_FILE_PATH]
    }

    @Unroll
    def "should throw exception that contains error message (#errorMessage) for file #filePath"()
    {
        given:
            def resolvedPath = resolveFilePath(filePath)

        when:
            tested.build(resolvedPath)

        then:
            def ex = thrown(YamlInvalidSchema)
            ex
            ex.getErrorMessages().contains(errorMessage)

        where:
            filePath                                        ||   errorMessage
            INVALID_ROOT_NODE_BLANK_FIELDS_FILE_PATH        ||  "grantee must not be blank"
            INVALID_ROOT_NODE_BLANK_FIELDS_FILE_PATH        ||  "equals_current_tenant_identifier_function_name must not be blank"
            INVALID_NESTED_NODE_BLANK_FIELDS_FILE_PATH      ||  "valid_tenant_value_constraint.is_tenant_valid_function_name must not be blank"
            INVALID_NESTED_NODE_BLANK_FIELDS_FILE_PATH      ||  "valid_tenant_value_constraint.tenant_identifiers_blacklist must not be null"
            INVALID_NESTED_NODE_EMPTY_LIST_FILE_PATH        ||  "valid_tenant_value_constraint.tenant_identifiers_blacklist must have at least one element"
            INVALID_LIST_NODES_BLANK_FIELDS_PATH            ||  "tables[3].rls_policy.name_for_function_that_checks_if_record_exists_in_table must not be blank"
            INVALID_LIST_NODES_BLANK_FIELDS_PATH            ||  "tables[0].name must not be blank"
    }
}
