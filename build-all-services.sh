#!/bin/bash

# Navigate to the project root directory
ROOT_DIR="$(dirname "$(realpath "$0")")"

SERVICES=("configserver" "accounts" "loans" "cards" )

# Function to format and build each service
build_service() {
    local service=$1
    echo "Formatting and building $service..."

    # Navigate to the service directory
    cd "$ROOT_DIR/$service" || { echo "Failed to enter $service directory"; exit 1; }

    # Format and build
    mvn spotless:apply || { echo "Formatting failed for $service"; exit 1; }
    mvn clean package || { echo "Build failed for $service"; exit 1; }

    # Return to the root directory
    cd "$ROOT_DIR" || { echo "Failed to return to root directory"; exit 1; }
}

# Build each service
for service in "${SERVICES[@]}"; do
    build_service "$service"
done

echo "All services formatted and built successfully."
