#!/bin/bash

# Check if the script received exactly one argument
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <day_number>"
    exit 1
fi

# Get the day number from the parameter
DAY_NUMBER="$1"

# Define the source and destination paths
COMMANDS=(
    "cp -r ./src/advent_of_code/template/ ./src/advent_of_code/day$DAY_NUMBER"
    "cp -r ./test/advent_of_code/template/ ./test/advent_of_code/day$DAY_NUMBER"
    "cp -r ./resources/advent_of_code/template/ ./resources/advent_of_code/day$DAY_NUMBER"
)

# Execute each command
for CMD in "${COMMANDS[@]}"; do
    echo "Executing: $CMD"
    eval $CMD
    if [ $? -ne 0 ]; then
        echo "Failed to execute: $CMD"
        exit 1
    fi
done

# Notify the user that the script is done
echo "All directories copied successfully."
