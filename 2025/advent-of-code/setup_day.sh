#!/bin/bash

# Usage: ./create_day.sh <n>
n="$1"

if [ -z "$n" ]; then
  echo "Error: Please provide a day number (n)."
  exit 1
fi

cp -R test/template "test/day$n"
cp -R src/template "src/day$n"
mkdir -p "resources/day$n"
touch "resources/day$n/input.txt"
touch "resources/day$n/acceptance-input.txt"

# Replace 'template' with 'day$n' in all new files
find "test/day$n" "src/day$n" -type f -exec sed -i "s/template/day$n/g" {} +

echo "Setup for day $n completed."