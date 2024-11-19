#!/bin/bash

# Directory containing Java files
TARGET_DIR="/Users/simonbeckmann/IdeaProjects/compliMySQL/src/main/java/orsk/compli/repository/neo4j"

# Check if the target directory exists
if [ ! -d "$TARGET_DIR" ]; then
  echo "Directory $TARGET_DIR does not exist."
  exit 1
fi

# Import statement for @Repository
IMPORT_STATEMENT="import org.springframework.stereotype.Repository;"

# Process each Java file in the target directory
find "$TARGET_DIR" -type f -name "*.java" | while read -r file; do
  # Ensure the import statement is beneath the package declaration
  if ! grep -q "$IMPORT_STATEMENT" "$file"; then
    awk -v import="$IMPORT_STATEMENT" '
      NR==1 { print $0 }  # Print the package statement (line 1)
      NR==2 { print import }  # Insert the import statement below
      NR>2 { print $0 }  # Print the rest of the file
    ' "$file" > "${file}.tmp" && mv "${file}.tmp" "$file"
    echo "Added import statement to $file"
  fi

  # Remove any existing @Repository annotation
  sed -i.bak '/@Repository/d' "$file"
  echo "Removed existing @Repository annotations from $file"

  # Extract the class name
  class_name=$(awk '/public interface/{print $3}' "$file")

  if [[ -z "$class_name" ]]; then
    echo "No interface definition found in file: $file"
    continue
  fi

  # Prepare the @Repository annotation for Neo4j
  neo4j_annotation="@Repository(\"neo4j${class_name}Repository\")"

  # Insert the Neo4j annotation before the interface declaration
  sed -i.bak "/public interface $class_name/i\\
$neo4j_annotation
" "$file"

  echo "Added Neo4j annotation to $file"
done

echo "Script execution completed."