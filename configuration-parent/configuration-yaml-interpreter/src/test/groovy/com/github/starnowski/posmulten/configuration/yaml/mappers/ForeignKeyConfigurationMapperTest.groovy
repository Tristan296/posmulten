package com.github.starnowski.posmulten.configuration.yaml.mappers

import com.github.starnowski.posmulten.configuration.core.model.ForeignKeyConfiguration

import static com.github.starnowski.posmulten.postgresql.test.utils.MapBuilder.mapBuilder

class ForeignKeyConfigurationMapperTest extends AbstractConfigurationMapperTest<com.github.starnowski.posmulten.configuration.yaml.model.ForeignKeyConfiguration, com.github.starnowski.posmulten.configuration.core.model.ForeignKeyConfiguration, ForeignKeyConfigurationMapper> {

    @Override
    protected Class<ForeignKeyConfiguration> getConfigurationObjectClass() {
        ForeignKeyConfiguration.class
    }

    @Override
    protected Class<com.github.starnowski.posmulten.configuration.yaml.model.ForeignKeyConfiguration> getYamlConfigurationObjectClass() {
        com.github.starnowski.posmulten.configuration.yaml.model.ForeignKeyConfiguration.class
    }

    @Override
    protected ForeignKeyConfigurationMapper getTestedObject() {
        new ForeignKeyConfigurationMapper()
    }

    @Override
    protected List<com.github.starnowski.posmulten.configuration.yaml.model.ForeignKeyConfiguration> prepareExpectedMappedObjectsList() {
        [
                new com.github.starnowski.posmulten.configuration.yaml.model.ForeignKeyConfiguration().setConstraintName("fk_constraint")
                        .setTableName("some_table")
                        .setForeignKeyPrimaryKeyColumnsMappings(mapBuilder().put("user_id", "id").build())
        ]
    }

    @Override
    protected List<ForeignKeyConfiguration> prepareExpectedUmnappeddObjectsList() {
        [
                new ForeignKeyConfiguration().setConstraintName("fk_constraint")
                        .setTableName("some_table")
                        .setForeignKeyPrimaryKeyColumnsMappings(mapBuilder().put("user_id", "id").build())
        ]
    }
}
