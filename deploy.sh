#!/bin/bash
set -e

echo "🔥 Pulling latest changes..."
git pull

echo "🔨 Building backend JAR..."
cd llama-chat-backend
./mvnw clean package -DskipTests
cd ..

echo "📦 Rebuilding backend Docker image..."
docker compose build backend

echo "🔁 Restarting backend container..."
docker compose down
docker compose up -d

echo "✅ Deployment complete"
echo "Backend URL: http://localhost:8000"
echo "LLaMA Server expected at: http://localhost:1488"

