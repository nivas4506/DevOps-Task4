# DevOps Task 4: Java Static Code Analysis with SonarQube

Professional static code analysis workspace configured with **SonarQube Community Edition (v9.9.8 LTS)**, **SonarScanner**, and **Apache Maven 3.9.9**.

---

## Workspace Directory Structure

```
DevOps-Task4/
├── docs/
│   └── SONARQUBE_COMMANDS_AND_CONFIG.txt    # Full reference documentation & commands log
├── scripts/
│   ├── start-sonar-jdk17.bat               # Launcher script for SonarQube server using JDK 17
│   ├── get_sonar_results.ps1               # PowerShell script to query SonarQube REST API
│   └── change_pass.ps1                     # Script to update default SonarQube credentials
├── tools/
│   ├── apache-maven-3.9.9/                 # Bundled Apache Maven 3.9.9 distribution
│   └── sonar-scanner-5.0.1.3006-windows/   # Bundled SonarScanner CLI distribution
├── sonarqube-9.9.8.100196/                 # SonarQube Server distribution directory
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── example/
│                   ├── App.java            # Application entry point
│                   ├── UserService.java    # Business logic service
│                   └── Calculator.java     # Utility math operations
├── target/                                 # Maven compiled binaries (target/classes)
├── pom.xml                                 # Maven build definition & Sonar plugin
├── sonar-project.properties                # SonarScanner project properties
└── README.md                               # Project documentation
```

---

## How to Run & Verify

### 1. Start SonarQube Server
Launch SonarQube Server with Java 17 environment:
```cmd
.\scripts\start-sonar-jdk17.bat
```
- **Web UI**: [http://localhost:9000](http://localhost:9000)
- **Status Endpoint**: [http://localhost:9000/api/system/status](http://localhost:9000/api/system/status)

### 2. Compile Java Source Code
```powershell
.\tools\apache-maven-3.9.9\bin\mvn.cmd compile
```

### 3. Run Static Code Analysis
Trigger SonarQube analysis:
```powershell
# Via Maven Sonar Plugin
.\tools\apache-maven-3.9.9\bin\mvn.cmd sonar:sonar "-Dsonar.host.url=http://localhost:9000" "-Dsonar.login=$env:SONAR_USER" "-Dsonar.password=$env:SONAR_PASS"

# Or via SonarScanner CLI
.\tools\sonar-scanner-5.0.1.3006-windows\bin\sonar-scanner.bat "-Dsonar.host.url=http://localhost:9000" "-Dsonar.login=$env:SONAR_USER" "-Dsonar.password=$env:SONAR_PASS"
```

### 4. Fetch Analysis Results via REST API
```powershell
powershell -ExecutionPolicy Bypass -File ".\scripts\get_sonar_results.ps1"
```

---

## Static Code Analysis Results Summary

| Metric | Result | Description |
| :--- | :--- | :--- |
| **Bugs** | **5** | 1 Blocker Resource Leak (`java:S2095`), 4 Major String Equality (`java:S4973`) |
| **Code Smells** | **27** | Cognitive Complexity > 15 (`java:S3776`), `System.out` logging (`java:S106`), Dead code |
| **Security Hotspots** | **1** | Hardcoded Credentials (`java:S2245` / Secret) |
| **Technical Debt** | **205 mins** | Estimated refactoring effort (~3.4 hours) |
| **Reliability Rating** | **E (5.0)** | Due to blocker resource leak bug |
| **Quality Gate** | **PASSED** | Successfully processed in SonarQube |
