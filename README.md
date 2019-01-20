# azzureapp

mvn clean install docker:build -DpushImage

docker run -it -p 8080:8080 pandyamitesh/azzureapp:0.0.1-SNAPSHOT

# rUN aPPLICATION 
mvn clean install spring-boot:run -Dspring-boot.run.profiles=local

# Remove alll images and containers from system 
docker container rm $(docker container ls -aq);
docker rmi $(docker images -a -q);
echo  done;


# Ask Git to Prompt for password

git config --local credential.helper ""
git push origin master

# Run RabbitMQ Docker locally
docker run -it  -p 15672:15672 -p 5672:5672 rabbitmq:3-management