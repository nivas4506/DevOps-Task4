@echo off
set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot"
set "SONAR_JAVA_PATH=C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot\bin\java.exe"
set "PATH=C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot\bin;%PATH%"

cd /d "%~dp0.."

if exist ".\tools\sonarqube-10.7.0.96327\temp" (
    rmdir /s /q ".\tools\sonarqube-10.7.0.96327\temp"
)

echo Starting SonarQube Server 10.7 (Latest Release) with JDK 17...
call ".\tools\sonarqube-10.7.0.96327\bin\windows-x86-64\StartSonar.bat"
