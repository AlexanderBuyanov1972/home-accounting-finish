# config-server-for-home-accounting-2ver

#start rabbit mq
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
#zipkin
docker run -d -p 9411:9411 openzipkin/zipkin

http://localhost:9411/zipkin/

#actuator
localhost:8012/actuator/

#refresh properties
http://localhost:8012/actuator/bus-refresh
POST -> 204 No content 
#encrypt
localhost:8012/encrypt
POST raw-text
#decrypt
localhost:8012/decrypt
POST raw-text

#create keytool
keytool -genkeypair -alias apiHomeBuhKey -keyalg RSA -dname "CN=smilyks programmer, OU=smilyks team, O=steam, L=Israel, S=Rehovot, C=Rehovot" -keypass 1q2w3e4r -keystore apiHomeBuhKey.jks -storepass 1q2w3e4r
