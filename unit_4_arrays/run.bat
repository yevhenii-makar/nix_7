call chcp 65001

call mvn clean package

call java -jar -Dfile.encoding=UTF8 target/chess.jar
