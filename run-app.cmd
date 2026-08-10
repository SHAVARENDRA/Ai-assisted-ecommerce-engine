@echo off
setlocal

REM ---------------------------------------------------------------------------
REM Start AI-Assisted E-Commerce Engine (Spring Boot)
REM Double-click this file or run: run-app.cmd
REM ---------------------------------------------------------------------------

cd /d "%~dp0"

REM Ensure PowerShell is available for Maven wrapper bootstrap if needed
set "PATH=%SystemRoot%\System32\WindowsPowerShell\v1.0;%PATH%"

REM Auto-detect JAVA_HOME when not set
if "%JAVA_HOME%"=="" (
  for /f "delims=" %%i in ('where java 2^>nul') do (
    set "JAVA_EXE=%%i"
    goto foundJava
  )
  echo ERROR: Java not found. Install JDK 17+ and add it to PATH.
  exit /b 1

  :foundJava
  for %%i in ("%JAVA_EXE%") do set "JAVA_HOME=%%~dpi.."
)

echo Starting AI-Assisted E-Commerce Engine on http://localhost:8080
echo JAVA_HOME=%JAVA_HOME%
echo.

call mvnw.cmd spring-boot:run

endlocal
