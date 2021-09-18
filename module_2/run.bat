call cd ../console_dialog
call mvn clean install

call cd ../module_2
call mvn clean package

call java -jar -Dfile.encoding=UTF8 target/module_2.jar
cmd /k