#!/bin/bash

# Define the package directory
PACKAGE_DIR="/Users/simonbeckmann/IdeaProjects/compli/src/main/java/orsk/compli/entities"

# Define the output file
OUTPUT_FILE="/Users/simonbeckmann/IdeaProjects/compli/src/main/java/orsk/compli/entities/extracted_code.txt"

# Check if the package directory exists
if [ ! -d "$PACKAGE_DIR" ]; then
  echo "Error: Directory $PACKAGE_DIR does not exist."
  exit 1
fi

# Find all Java files in the package and concatenate them into the output file
find "$PACKAGE_DIR" -name "*.java" -type f -exec cat {} + > "$OUTPUT_FILE"

# Confirmation message
echo "All Java code from $PACKAGE_DIR has been written to $OUTPUT_FILE"