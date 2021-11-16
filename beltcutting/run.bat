call docker-compose up -d db
call mvn clean package
call docker-compose up -d --build

start http://localhost:8080/swagger-ui.html