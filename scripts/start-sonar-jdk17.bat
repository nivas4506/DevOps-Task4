@echo off
set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot"
set "SONAR_JAVA_PATH=C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot\bin\java.exe"
set "PATH=C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot\bin;%PATH%"

cd /d "%~dp0.."

if exist ".\sonarqube-9.9.8.100196\bin\windows-x86-64\StartSonar.bat" (
    call ".\sonarqube-9.9.8.100196\bin\windows-x86-64\StartSonar.bat"
) else if exist ".\tools\sonarqube-9.9.8.100196\bin\windows-x86-64\StartSonar.bat" (
    call ".\tools\sonarqube-9.9.8.100196\bin\windows-x86-64\StartSonar.bat"
)
