FROM amazoncorretto:11

ARG JAR_FILE
ARG ARTIFACT_ID

EXPOSE 8081

#ADD ./target/${JAR_FILE} app.jar
ADD ./target/kinesis-consumer-1.0-SNAPSHOT.jar app.jar

RUN sh -c 'touch /app.jar'

ENV JAVA_OPTS=""

# Regarding settings of java.security.egd, see http://wiki.apache.org/tomcat/HowTo/FasterStartUp#Entropy_Source
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=sit -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]