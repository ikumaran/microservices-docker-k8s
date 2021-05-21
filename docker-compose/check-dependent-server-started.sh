#!/bin/bash

apt-get update -y

yes | apt-get install curl

# check for healthy config server
curlResult=$(curl -s -o /dev/null -I -w "%{http_code}" http://config-server:7001/actuator/health)

echo "result status code:" "$curlResult"

while [[ ! $curlResult == "200" ]]; do
  >&2 echo "Config server is not up yet!"
  sleep 2
  curlResult=$(curl -s -o /dev/null -I -w "%{http_code}" http://config-server:7001/actuator/health)
done
echo "Config server started..."

# check for healthy service-registry
curlResult=$(curl -s -o /dev/null -I -w "%{http_code}" http://service-registry:6001/actuator/health)

echo "result status code:" "$curlResult"

while [[ ! $curlResult == "200" ]]; do
  >&2 echo "Service registry is not up yet!"
  sleep 2
  curlResult=$(curl -s -o /dev/null -I -w "%{http_code}" http://service-registry:6001/actuator/health)
done
echo "Service registry started..."

# Once both services good - start the container's entry point
/usr/local/openjdk-11/bin/java -jar -Djasypt.encryptor.password=$SECRET_PASSWORD /app/app.jar
