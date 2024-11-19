#!/bin/bash

# Define the directory to scan
TARGET_DIR="/Users/simonbeckmann/IdeaProjects/compliMySQL/src/main/java/orsk/compli/service/neo4j"

# Define the incorrect and correct imports
OLD_IMPORT1="import orsk.compli.entities.jpa"
NEW_IMPORT1="import orsk.compli.entities.neo4j."
OLD_IMPORT2="import orsk.compli.repository.jpa."
NEW_IMPORT2="import orsk.compli.repository.neo4j."

# Find all Java files and process them
find "$TARGET_DIR" -type f -name "*.java" | while read -r file; do
    echo "Processing $file"

    # Replace old imports with the correct ones
    sed -i.bak "s|$OLD_IMPORT1|$NEW_IMPORT1|g" "$file"
    sed -i.bak "s|$OLD_IMPORT2|$NEW_IMPORT2|g" "$file"

    # Remove backup files if you don't want to keep them
    rm "${file}.bak"
done

echo "Import corrections completed."