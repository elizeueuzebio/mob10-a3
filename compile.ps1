$ErrorActionPreference = "Stop"

function Get-JdkMajor {
    param([string]$JdkHome)

    $releaseFile = Join-Path $JdkHome "release"
    if (Test-Path $releaseFile) {
        $release = Get-Content $releaseFile -Raw
        if ($release -match 'JAVA_VERSION="(?:1\.)?(\d+)') {
            return [int]$Matches[1]
        }
    }

    if ($JdkHome -match 'jdk-?(\d+)') {
        return [int]$Matches[1]
    }

    return 0
}

function Resolve-JdkCommand {
    param([string]$CommandName)

    $homes = @()
    if ($env:JAVA_HOME) {
        $homes += $env:JAVA_HOME
    }

    $javaRoot = "C:\Program Files\Java"
    if (Test-Path $javaRoot) {
        $homes += Get-ChildItem -Path $javaRoot -Directory -Filter "jdk*" | ForEach-Object { $_.FullName }
    }

    $bestHome = $homes |
        Where-Object { Test-Path (Join-Path $_ "bin\$CommandName") } |
        Select-Object -Unique |
        ForEach-Object {
            [pscustomobject]@{
                Home = $_
                Major = Get-JdkMajor $_
            }
        } |
        Sort-Object -Property Major, Home -Descending |
        Select-Object -First 1

    if ($bestHome) {
        return Join-Path $bestHome.Home "bin\$CommandName"
    }

    return (Get-Command $CommandName -ErrorAction Stop).Source
}

$projectRoot = $PSScriptRoot
$binDir = Join-Path $projectRoot "bin"
$javac = Resolve-JdkCommand "javac.exe"
$sourceFiles = Get-ChildItem -Path (Join-Path $projectRoot "src") -Recurse -Filter "*.java" |
    Sort-Object FullName |
    ForEach-Object { $_.FullName }

if (@($sourceFiles).Count -eq 0) {
    throw "Nenhum arquivo .java encontrado em src."
}

if (!(Test-Path $binDir)) {
    New-Item -ItemType Directory -Path $binDir | Out-Null
}

Write-Host "Compilando com: $javac"
& $javac -version
& $javac -encoding UTF-8 -d $binDir $sourceFiles
Write-Host "Compilacao concluida em: $binDir"
