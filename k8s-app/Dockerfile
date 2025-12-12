FROM eclipse-temurin:21-jre
WORKDIR /k8s-app
COPY ./build/libs/k8s-app.jar ./latest.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "latest.jar"]
#CMD ["java", "-jar", "latest.jar"]
USER 1001
