FROM registry.cn-beijing.aliyuncs.com/common-space/openjdk:11.0.9-jre
ARG PROJECT_NAME
ARG PROJECT_VERSION
ARG ENVIRONMENT
ENV ENVIRONMENT_VAR=$ENVIRONMENT
COPY target/${PROJECT_NAME}-${PROJECT_VERSION}.jar app.jar
RUN cp "/usr/share/zoneinfo/Asia/Shanghai" "/etc/localtime"

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Duser.timezone=GMT+08", "-jar", "/app.jar", "--spring.profiles.active=${ENVIRONMENT_VAR}"]