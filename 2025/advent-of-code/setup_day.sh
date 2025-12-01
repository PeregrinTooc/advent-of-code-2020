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

echo "Setup for day $n completed."