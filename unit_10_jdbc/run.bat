call mvn clean package

call java -jar -Dfile.encoding=UTF8 target/jdbc.jar
cmd /k