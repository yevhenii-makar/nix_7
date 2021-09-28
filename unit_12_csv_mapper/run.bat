call mvn clean package

call java -jar -Dfile.encoding=UTF8 target/csv_mapper.jar students.csv
cmd /k