FROM adoptopenjdk/openjdk15:ubi
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY build/artifacts/Labo_jar/*.jar app.jar
EXPOSE 8081
CMD ["java", "-jar", "app.jar"]