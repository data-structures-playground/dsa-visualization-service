#!/usr/bin/env bash

# DSNA Visualization Service API examples.

BASE_URL="${BASE_URL:-http://localhost:8081}"

# Create array state.
curl --request POST "${BASE_URL}/api/v1/arrays" \
  --header "Content-Type: application/json" \
  --data '{
    "values": [5, 2, 8, 1]
  }'

# Read current array state.
curl --request GET "${BASE_URL}/api/v1/arrays"

# Update the value at array index 1.
curl --request PUT "${BASE_URL}/api/v1/arrays/1" \
  --header "Content-Type: application/json" \
  --data '{
    "value": 7
  }'

# Delete the value at array index 1.
curl --request DELETE "${BASE_URL}/api/v1/arrays/1"
