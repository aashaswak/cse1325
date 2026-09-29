@echo off
setlocal
cd /d "%~dp0"
call ant compile
if errorlevel 1 exit /b 1
java --add-modules jdk.httpserver -cp target web.MavTutorWeb
