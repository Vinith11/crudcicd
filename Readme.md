docker build -t spring-cicdaction-crud .

docker run -p 8080:8080 -p 9090:9090 -p 3000:3000 --env-file .env spring-cicdaction-crud -d

Grafana default username: admin password: admin