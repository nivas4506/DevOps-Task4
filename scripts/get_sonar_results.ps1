$user = if ($env:SONAR_USER) { $env:SONAR_USER } else { 'admin' }
$pass = if ($env:SONAR_PASS) { $env:SONAR_PASS } else { 'admin' }

$pair = "${user}:${pass}"
$bytes = [System.Text.Encoding]::ASCII.GetBytes($pair)
$base64 = [System.Convert]::ToBase64String($bytes)
$headers = @{ Authorization = "Basic $base64" }

try {
    Write-Host "=== SONARQUBE MEASURES ===" -ForegroundColor Cyan
    $measuresUrl = "http://localhost:9000/api/measures/component?component=java-sonarqube-demo&metricKeys=bugs,code_smells,vulnerabilities,security_hotspots,sqale_index,reliability_rating,sqale_rating,security_rating,cognitive_complexity,ncloc"
    $measures = Invoke-RestMethod -Uri $measuresUrl -Headers $headers
    $measures.component.measures | Format-Table -AutoSize

    Write-Host "`n=== SONARQUBE ISSUES DETAILED BREAKDOWN ===" -ForegroundColor Yellow
    $issuesUrl = "http://localhost:9000/api/issues/search?componentKeys=java-sonarqube-demo&ps=100"
    $issues = Invoke-RestMethod -Uri $issuesUrl -Headers $headers
    $issues.issues | Select-Object key, rule, severity, type, component, line, message | Format-Table -AutoSize
} catch {
    Write-Host "Authorization required or invalid credentials provided via SONAR_USER / SONAR_PASS environment variables." -ForegroundColor Red
}
