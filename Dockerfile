FROM java:8
WORKDIR /
RUN pwd
CMD pwd
ADD build/libs/NightTimeTemperature-0.0.1-SNAPSHOT-DEV.jar build/libs/NightTimeTemperature-0.0.1-SNAPSHOT-DEV.jar
EXPOSE 8080
CMD java -jar build/libs/NightTimeTemperature-0.0.1-SNAPSHOT-DEV.jar "dev"


