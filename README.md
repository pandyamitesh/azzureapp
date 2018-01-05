# azzureapp

mvn clean install docker:build -DpushImage

docker run -it -p 8080:8080 pandyamitesh/azzureapp:0.0.1-SNAPSHOT