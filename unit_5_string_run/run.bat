call cd ..
call cd unit_5_strings
call mvn clean package install
call cd..\unit_5_string_run
call mvn clean package
call  java -jar target/reversestringrun.jar