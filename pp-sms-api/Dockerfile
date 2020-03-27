#BUILD STAGE
FROM maven AS MAVEN_BUILD

MAINTAINER Mahmoud Yahia

COPY repository /root/.m2/repository

COPY pom.xml /build/

COPY src /build/src/

WORKDIR /build/

RUN mvn package

#DEPLOY STAGE
FROM openjdk

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/school-management-sysem-0.0.1-SNAPSHOT.jar /app/

COPY wait-for-it.sh /app/

RUN chmod 775 wait-for-it.sh

ENTRYPOINT ["./wait-for-it.sh", "db:3306", "--", "java", "-jar", "school-management-sysem-0.0.1-SNAPSHOT.jar"]
