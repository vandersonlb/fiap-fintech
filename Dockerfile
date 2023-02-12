# FROM openjdk:18.0.2-slim-buster as build

# WORKDIR /data
# COPY . .
# RUN jar -cvf fintech.war *

# FROM tomcat:9.0.71-jre17-temurin-focal

# COPY --from=build /data/fintech.war /usr/local/tomcat/webapps/fintech.war

FROM tomcat:9.0.71-jre17-temurin-focal
COPY Fintech.war /usr/local/tomcat/webapps/fintech.war
