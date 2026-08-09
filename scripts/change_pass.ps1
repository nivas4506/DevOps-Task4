$user = if ($env:SONAR_USER) { $env:SONAR_USER } else { 'admin' }
$oldPass = if ($env:SONAR_OLD_PASS) { $env:SONAR_OLD_PASS } else { 'admin' }
$newPass = $env:SONAR_NEW_PASS

if (-not $newPass) {
    Write-Host "Please set SONAR_NEW_PASS environment variable before running this script." -ForegroundColor Yellow
    exit 1
}

$pair = "${user}:${oldPass}"
$bytes = [System.Text.Encoding]::ASCII.GetBytes($pair)
$tok = [System.Convert]::ToBase64String($bytes)
$h = @{ Authorization = "Basic $tok" }
Invoke-RestMethod -Method Post -Uri "http://localhost:9000/api/users/change_password?login=${user}&previousPassword=${oldPass}&password=${newPass}" -Headers $h
