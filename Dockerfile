FROM eclipse-temurin:23-jdk AS builder

ARG COMPILE_DIR=/compiledir


WORKDIR ${COMPILE_DIR}

COPY ./mvnw .
COPY .mvn .mvn

COPY pom.xml .
COPY src src

RUN chmod a+x ./mvnw && ./mvnw package -Dmaven.test.skip=true 

ENV SERVER_PORT=3000

EXPOSE ${SERVER_PORT}

#ENTRYPOINT java -jar target/vttp.batchb.ssf.day18-0.0.1-SNAPSHOT.jar


#second stage
FROM eclipse-temurin:23-jdk

ARG WORK_DIR=/app

WORKDIR ${WORK_DIR}

COPY --from=builder /compiledir/target/noticeboard-0.0.1-SNAPSHOT.jar vttp-ssf-day19.jar

ENV SERVER_PORT=3000

EXPOSE ${SERVER_PORT}
SHELL [ "/bin/sh", "-c" ]
ENTRYPOINT java -jar vttp-ssf-day19.jar

HEALTHCHECK --interval=30s --timeout=60s --start-period=120s --retries=3 CMD curl -s -f http:/localhost:4000/demo/health || exit 1