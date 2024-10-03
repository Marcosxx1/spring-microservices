#!/bin/bash

# Define your Docker Hub account name
DOCKER_HUB_ACCOUNT="marcossata" # Replace with your actual Docker Hub account name. This one will only work for me.

# Define the images and tags to be pushed
IMAGES=("accounts" "cards" "loans" "config-server")
TAG="USING_MYSQL" ## TODO - WHEN PUSHING THE IMAGE.  CHANGE THIS TO THE TAG WE ARE USING IN SERVICES=( ) OF create-docker-images.sh
CONFIG_SERVER_TAG="USING_MYSQL" ## TODO - WHEN PUSHING THE IMAGE.  CHANGE THIS TO THE TAG WE ARE USING IN SERVICES=( ) OF create-docker-images.sh

# Login to Docker Hub (if not logged in)
docker login || { echo "Docker login failed"; exit 1; }

# Function to push an image
push_image() {
    local image=$1
    local tag=$2
    echo "Pushing $DOCKER_HUB_ACCOUNT/$image:$tag to Docker Hub..."

    # Tag and push the image
    docker image push docker.io/$DOCKER_HUB_ACCOUNT/$image:$tag || { echo "Failed to push $image:$tag"; exit 1; }
}

# Push each service's image
for image in "${IMAGES[@]}"; do
    if [ "$image" == "config-server" ]; then
        push_image "$image" "$CONFIG_SERVER_TAG"
    else
        push_image "$image" "$TAG"
    fi
done

echo "All images have been successfully pushed to Docker Hub."
