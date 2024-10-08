#!/bin/bash

# Navigate to the project root directory
ROOT_DIR="$(dirname "$(realpath "$0")")"

# List of service directories and their respective Docker tags
declare -A SERVICES
SERVICES=( # We can change the tag for each service
    ["accounts"]="accounts:USING_MYSQL"
    ["loans"]="loans:USING_MYSQL"
    ["cards"]="cards:USING_MYSQL"
    ["configserver"]="config-server:USING_MYSQL"
)

# Function to build Docker images for each service
build_docker_image() {
    local service=$1
    local tag=$2
    echo "Building Docker image for $service..."

    # Navigate to the service directory
    cd "$ROOT_DIR/$service" || { echo "Failed to enter $service directory"; exit 1; }

    # Build Docker image
    docker build . -t "marcossata/$tag" -f "Dockerfile.$service" || { echo "Docker build failed for $service"; exit 1; }

    # Return to the root directory
    cd "$ROOT_DIR" || { echo "Failed to return to root directory"; exit 1; }
}

# Build Docker images for each service
for service in "${!SERVICES[@]}"; do
    build_docker_image "$service" "${SERVICES[$service]}"
done

echo "Docker images created for all services successfully."
