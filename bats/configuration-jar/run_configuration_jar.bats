
function setup {
  #Save previous password
  PREVIOUS_PGPASSWORD="$PGPASSWORD"
  export TIMESTAMP=`date +%s`
  export CONFIGURATION_JAR_TARGET_DIR="$BATS_TEST_DIRNAME/../../configuration-parent/configuration-jar/target"
  export CONFIGURATION_JAR_NAME=`find "$CONFIGURATION_JAR_TARGET_DIR" -name '*-jar-with-dependencies.jar'`
  #TODO directory with tests configuration
  export CONFIGURATION_YAML_TEST_RESOURCES_DIR_PATH="$BATS_TEST_DIRNAME/../../configuration-parent/configuration-yaml-interpreter/src/test/resources/com/github/starnowski/posmulten/configuration/yaml"
  mkdir -p "$BATS_TMPDIR/$TIMESTAMP"
}

@test "Run executable jar file with passed java properties for valid configuration file" {
  #given
  CONFIGURATION_FILE_PATH="$CONFIGURATION_YAML_TEST_RESOURCES_DIR_PATH/all-fields.yaml"
  [ -f "$CONFIGURATION_FILE_PATH" ]
  # Results files
  [ ! -f "$BATS_TMPDIR/$TIMESTAMP/create_script.sql" ]
  [ ! -f "$BATS_TMPDIR/$TIMESTAMP/drop_script.sql" ]

  #when
  run java -Dposmulten.configuration.config.file.path="$CONFIGURATION_FILE_PATH" -Dposmulten.configuration.create.script.path="$BATS_TMPDIR/$TIMESTAMP/create_script.sql" -Dposmulten.configuration.drop.script.path="$BATS_TMPDIR/$TIMESTAMP/drop_script.sql" -jar "$CONFIGURATION_JAR_NAME"

  #then
  echo "output is --> $output <--"  >&3
  [ "$status" -eq 0 ]
  [ -f "$BATS_TMPDIR/$TIMESTAMP/create_script.sql" ]
  [ -f "$BATS_TMPDIR/$TIMESTAMP/drop_script.sql" ]

  #Smoke tests for scripts content
  grep 'CREATE POLICY' "$BATS_TMPDIR/$TIMESTAMP/create_script.sql"
  [ "$?" -eq 0 ]
  grep 'DROP POLICY IF EXISTS' "$BATS_TMPDIR/$TIMESTAMP/drop_script.sql"
  [ "$?" -eq 0 ]
}

@test "Run executable jar file with passed java properties for invalid configuration file" {
  #given
  CONFIGURATION_FILE_PATH="$CONFIGURATION_YAML_TEST_RESOURCES_DIR_PATH/invalid-list-nodes-blank-fields.yaml"
  [ -f "$CONFIGURATION_FILE_PATH" ]
  # Results files
  [ ! -f "$BATS_TMPDIR/$TIMESTAMP/create_script.sql" ]
  [ ! -f "$BATS_TMPDIR/$TIMESTAMP/drop_script.sql" ]

  #when
  run java -Dposmulten.configuration.config.file.path="$CONFIGURATION_FILE_PATH" -Dposmulten.configuration.create.script.path="$BATS_TMPDIR/$TIMESTAMP/create_script.sql" -Dposmulten.configuration.drop.script.path="$BATS_TMPDIR/$TIMESTAMP/drop_script.sql" -jar "$CONFIGURATION_JAR_NAME"

  #then
  echo "output is --> $output <--"  >&3
  [ "$status" -eq 1 ]
  [ ! -f "$BATS_TMPDIR/$TIMESTAMP/create_script.sql" ]
  [ ! -f "$BATS_TMPDIR/$TIMESTAMP/drop_script.sql" ]

  #Smoke tests for validation messages
  echo "$output" > "$BATS_TMPDIR/$TIMESTAMP/output"
  grep 'SEVERE: Posmulten invalid configuration' "$BATS_TMPDIR/$TIMESTAMP/output"
  [ "$?" -eq 0 ]
  grep 'SEVERE: Configuration error: tables[3].rls_policy.name_for_function_that_checks_if_record_exists_in_table must not be blank' "$BATS_TMPDIR/$TIMESTAMP/output"
  [ "$?" -eq 0 ]
}

function teardown {
  rm -rf "$BATS_TMPDIR/$TIMESTAMP"
}