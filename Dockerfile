FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

# Install necessary tools
RUN apt-get update && apt-get install -y wget gnupg

# Install Prometheus
RUN wget https://github.com/prometheus/prometheus/releases/download/v2.37.0/prometheus-2.37.0.linux-amd64.tar.gz \
    && tar xvfz prometheus-*.tar.gz \
    && mv prometheus-2.37.0.linux-amd64 /prometheus \
    && rm prometheus-*.tar.gz

# Install Grafana
RUN wget -q -O - https://packages.grafana.com/gpg.key | apt-key add - \
    && echo "deb https://packages.grafana.com/oss/deb stable main" | tee -a /etc/apt/sources.list.d/grafana.list \
    && apt-get update \
    && apt-get install -y grafana

# Copy Prometheus configuration
COPY prometheus.yml /prometheus/prometheus.yml

# Copy Grafana configuration (if you have any custom settings)
# COPY grafana.ini /etc/grafana/grafana.ini

# Expose ports
EXPOSE 8080
EXPOSE 9090
EXPOSE 3000

# Add your application JAR
COPY --from=build target/spring-cicdaction-crud.jar spring-cicdaction-crud.jar

# Start your application, Prometheus, and Grafana
CMD java -jar /spring-cicdaction-crud.jar $(for var in $(compgen -e); do echo "-D$var=${!var}"; done) & \
    /prometheus/prometheus --config.file=/prometheus/prometheus.yml & \
    grafana-server --homepath /usr/share/grafana --config /etc/grafana/grafana.ini