#!/bin/bash

# Navigate to the project root directory
ROOT_DIR="$(dirname "$(realpath "$0")")"

# List of service directories
SERVICES=("accounts" "loans" "cards")

# Function to format and build each service
build_service() {
    local service=$1
    echo "Formatting and building $service..."
    cd "$ROOT_DIR/$service"
    mvn spotless:apply
    mvn clean package
    cd "$ROOT_DIR"
}

# Build each service
for service in "${SERVICES[@]}"; do
    build_service "$service"
done

echo "All services formatted and built successfully."
