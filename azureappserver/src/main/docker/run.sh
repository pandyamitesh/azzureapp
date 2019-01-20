#!/bin/sh
echo "********************************************************"
echo "Starting Spring Boot Service";
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -jar /usr/local/server/@project.build.finalName@.jar