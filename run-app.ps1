# Start AI-Assisted E-Commerce Engine (Spring Boot)
# Run from project root: .\run-app.ps1

$ErrorActionPreference = "Stop"
Set-Location $PSScriptRoot

if (-not $env:JAVA_HOME) {
    $java = Get-Command java -ErrorAction SilentlyContinue
    if (-not $java) {
        Write-Error "Java not found. Install JDK 17+ and add it to PATH."
    }
    $env:JAVA_HOME = Split-Path (Split-Path $java.Source -Parent) -Parent
}

Write-Host "Starting AI-Assisted E-Commerce Engine on http://localhost:8080"
Write-Host "JAVA_HOME=$($env:JAVA_HOME)"
Write-Host ""

& .\mvnw.cmd spring-boot:run
