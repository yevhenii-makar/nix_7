call cd ../console_dialog
call mvn clean install

call cd ../unit_9_ionio
call mvn clean package

call java -jar -Dfile.encoding=UTF8 target/ionio.jar
cmd /k