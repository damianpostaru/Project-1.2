FROM openjdk:15-jdk-oraclelinux8

COPY . /opt/Project-1.2/

WORKDIR /opt/Project-1.2/

RUN ./gradlew clean build --no-daemon -x test

WORKDIR /opt/Project-1.2/build/libs/

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar Project-1.2-1.0-SNAPSHOT.jar" ]