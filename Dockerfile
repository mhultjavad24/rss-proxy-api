FROM eclipse-temurin:21-jdk-alpine as build
WORKDIR /workspace/app

COPY gradle gradle
COPY build.gradle settings.gradle gradlew ./
COPY src src

RUN ./gradlew bootJar -x test
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)

FROM eclipse-temurin:21-jre-alpine
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/build/dependency

# Create a non-root user to run the application
RUN addgroup --system --gid 1001 appuser && \
    adduser --system --uid 1001 --ingroup appuser appuser

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Set proper permissions
RUN chown -R appuser:appuser /app
USER appuser

# Enable security options
ENTRYPOINT ["java", \
            "-cp", "app:app/lib/*", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-Dspring.profiles.active=production", \
            "-XX:+UseContainerSupport", \
            "-XX:MaxRAMPercentage=75.0", \
            "-Djava.awt.headless=true", \
            "io.github.mhultjavad24.rss_proxy_api.RssProxyApiApplication"] 