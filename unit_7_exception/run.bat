call cd ../console_dialog
call mvn clean install

call cd ../unit_7_exception
call mvn clean package

call java -jar -Dfile.encoding=UTF8 target/custom-date.jar