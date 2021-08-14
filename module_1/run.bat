call cd ../console_dialog
call mvn clean install

call cd ../module_1
call mvn clean package

call java -jar -Dfile.encoding=UTF8 target/module1.jar