FROM openjdk:25-ea-jdk-oraclelinux9

WORKDIR /app

COPY ./target/ target/


# We change the name of the build file into lab4 in pom.xml
ENTRYPOINT ["java", "-jar", "target/mini2.jar"]