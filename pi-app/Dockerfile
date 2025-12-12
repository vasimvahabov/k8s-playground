FROM eclipse-temurin:21-jre
WORKDIR /pi-app
COPY build/libs/pi-app.jar ./latest.jar
COPY liveness-check.sh .
EXPOSE 9091
RUN chmod +x liveness-check.sh
ENTRYPOINT ["java", "-jar", "latest.jar"]
#CMD ["java", "-jar", "latest.jar"]
USER 1001
