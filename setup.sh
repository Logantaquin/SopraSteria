#!/bin/bash

# Récupération du chemin absolu du projet
PROJECT_DIR="$(cd "$(dirname "$0")" && pwd)"

echo "Installation des dépendances..."
cd "$PROJECT_DIR/front" && npm install
cd "$PROJECT_DIR/back" && ./mvnw install

echo "Démarrage des services..."
gnome-terminal -- bash -c "cd $PROJECT_DIR/front && ng serve; exec bash"
gnome-terminal -- bash -c "cd $PROJECT_DIR/back && java -jar target/backend.jar; exec bash"
gnome-terminal -- bash -c "mongod --dbpath $PROJECT_DIR/src/main/data/db; exec bash"

echo "Tout est prêt !"