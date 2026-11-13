# Requires JDK 21. stubs/ only exist so javac can compile; they aren't packaged.
$ErrorActionPreference = "Stop"
Set-Location $PSScriptRoot
$version = (Select-String res\META-INF\neoforge.mods.toml -Pattern '^version = "(.+)"').Matches[0].Groups[1].Value
Remove-Item -Recurse -Force stubout, out -ErrorAction SilentlyContinue
javac --release 21 -d stubout (Get-ChildItem stubs -Recurse -Filter *.java).FullName
javac --release 21 -cp stubout -d out (Get-ChildItem src -Recurse -Filter *.java).FullName
Copy-Item res\* out -Recurse -Force
Copy-Item LICENSE out -Force
jar --create --file "dtskylandsfix-$version.jar" --manifest manifest.mf -C out .
