# Récupération du chemin absolu du projet
$ProjectDir = Split-Path -Parent $MyInvocation.MyCommand.Definition

Write-Host "Installation des dépendances..."
Set-Location "$ProjectDir\front"
npm install
Set-Location "$ProjectDir\back"
.\mvnw install

Write-Host "Démarrage des services..."
Start-Process -NoNewWindow -FilePath "ng" -ArgumentList "serve" -WorkingDirectory "$ProjectDir\front"
Start-Process -NoNewWindow -FilePath "java" -ArgumentList "-jar target/backend.jar" -WorkingDirectory "$ProjectDir\back"
Start-Process -NoNewWindow -FilePath "mongod" -ArgumentList "--dbpath $ProjectDir\src\main\data\db"

Write-Host "Tout est prêt !"