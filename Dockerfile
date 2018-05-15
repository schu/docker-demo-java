FROM java:9-jdk as build

COPY HelloServer.java /helloserver/

WORKDIR /helloserver

RUN javac HelloServer.java

FROM java:9-jre

COPY --from=build /helloserver/ /helloserver/

WORKDIR /helloserver

CMD ["java", "HelloServer"]
