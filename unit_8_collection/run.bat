call cd ../console_dialog
call mvn clean install

call cd ../unit_8_collection
call mvn clean package

call java -jar -Dfile.encoding=UTF8 target/collection.jar
cmd /k