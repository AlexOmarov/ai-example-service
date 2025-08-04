# This is a Dockerfile for building a Docker image for the оjvm service.
# The service itself is built by gradle and the result is copied into the image.
# The image is based on a runtime-only Liberica JDK image.
# Runs with custom user, exposes main and jmx ports.
# Uses /var/jfr and /var/dumps folders for storing profiling data and heap dumps.

FROM bellsoft/liberica-runtime-container:jre-23-slim-glibc

RUN addgroup --system --gid 800 appuser && \
    adduser --system --uid 800 appuser && \
    mkdir /var/jfr && \
    mkdir /var/dumps && \
    chown -R appuser:appuser /var/jfr /var/dumps

USER appuser

COPY app/build/libs/ /
COPY tdlib/ /usr/lib
COPY jfr/ /var/jfr

EXPOSE 8080 9010

ENTRYPOINT ["java", \
    "-XX:StartFlightRecording:filename=/var/jfr/data.jfr,name=NewsServiceProfiling,delay=10s,dumponexit=true,maxsize=500M,maxage=30m,disk=true,settings=/var/jfr/profile.jfc", \
    "-XX:FlightRecorderOptions:repository=/var/jfr", \
    "-XX:+HeapDumpOnOutOfMemoryError", \
    "-XX:HeapDumpPath=/var/dumps", \
    "-Dcom.sun.management.jmxremote", \
    "-Dcom.sun.management.jmxremote.port=9010", \
    "-Dcom.sun.management.jmxremote.rmi.port=9010", \
    "-Dcom.sun.management.jmxremote.local.only=false", \
    "-Dcom.sun.management.jmxremote.authenticate=false", \
    "-Dcom.sun.management.jmxremote.ssl=false", \
    "-Djava.rmi.server.hostname=0.0.0.0", \
    "-jar", "/app.jar"]
