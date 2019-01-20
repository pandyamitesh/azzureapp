# azzureapp

mvn clean install docker:build -DpushImage

docker run -it -p 8080:8080 pandyamitesh/azzureapp:0.0.1-SNAPSHOT

# rUN aPPLICATION 
mvn clean install spring-boot:run

# Remove alll images and containers from system 
docker container rm $(docker container ls -aq);
docker rmi $(docker images -a -q);
echo  done;
