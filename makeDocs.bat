echo off
cd src
javadoc.exe -d docs practic exception
cd ..
xcopy /D/E/Y src\docs docs\
rmdir /q/s src\docs
pause