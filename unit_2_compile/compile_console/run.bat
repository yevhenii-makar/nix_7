@echo off
cls
echo #############################################################
echo remove old build compile_console
echo #############################################################

rmdir /s /q .\build

echo #############################################################
echo start compilation compile_console
echo #############################################################


javac -sourcepath .\src -d build\classes -cp .\lib\commons-math3-3.6.1.jar;lib\commons-text-1.9.jar;lib\commons-lang3-3.11.jar src\com\yevheniimakar\console\test\test2\Test2.java src\com\yevheniimakar\console\test\Test1.java src\com\yevheniimakar\console\Main.java

echo #############################################################
echo run compile_console
echo #############################################################

java -cp build\classes\;.\lib\commons-math3-3.6.1.jar;.\lib\commons-text-1.9.jar;.\lib\commons-lang3-3.11.jar  com.yevheniimakar.console.Main

echo #############################################################
echo END
echo #############################################################
