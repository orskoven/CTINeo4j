#!/bin/bash

# Define the directory to scan
TARGET_DIR="/Users/simonbeckmann/IdeaProjects/compliMySQL/src/main/java/orsk/compli/service/neo4j"

# Define the search and replace strings(Long.valueOf(String.valueOf(id)))
SEARCH_STRING="(Long.valueOf(String.valueOf(id)))"String.valueOf(id)
REPLACE_STRING="(String.valueOf(id))"

# Find all Java files and process them
find "$TARGET_DIR" -type f -name "*.java" | while read -r file; do
    echo "Processing $file"

    # Replace the search string with the replace string
    sed -i.bak "s|$SEARCH_STRING|$REPLACE_STRING|g" "$file"

    # Remove backup files if you don't want to keep them
    rm "${file}.bak"
done

echo "String replacement completed."