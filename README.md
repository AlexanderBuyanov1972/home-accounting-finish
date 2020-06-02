# home-accounting-finish - microservices.

<-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <->  
# config-server-for-home-accounting

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

<-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <->  
# currency-service-for-home-accounting

#details of configuration
localhost:8012/currency-service/detail 

#actuator of currency-service from zuul
localhost:8011/currency-service/actuator/

#create currency for one user
localhost:8011/currency-service/currency/

{
	 "currencyName" : "Rubl rossiyskiy",
     "userUuid":"42be74e6-dfc8-4406-8405-a273a4dd3bd2",
     "abbr":"RUB"
}

<-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> 
# eureka service for home accounting
http://localhost:8010/

# zuul-api-gateway-for-home-accounting-2ver
#check user-service 
localhost:8011/users-service/status/check

#actuator
localhost:8011/actuator/

<-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> 
# users-for-home-accounting

#start users-service
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.application.instance_id=smilyk, --server.port=8999"
#h2-database-console from zuul
localhost:8011/users-service/h2-console
where available -> make from logs
#create user from zuul
localhost:8011/users-service/users/

= / = / = / = / = / = / = / = / = / = /

{
	"userName":"user1",
    "firstName":"firstName",
    "lastName":"lastName",
    "email":"mail@mail.com",
    "gender":"MALE",
    "password":"test"
}

#login user
localhost:8011/users-service/users/login

= / = / = / = / = / = / = / = / = / = /
{
    "email":"mail@mail.com",
    "password":"test"
}

#actuator
localhost:8011/user-service/actuator/
#details of configuration
localhost:8012/users-service/detail
GET !!! with token!!!

<-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> <-> 
# bills-for-home-accounting

#actuator from zuul
localhost:8011/bills-service/actuator/
#create bill
localhost:8011/bills-service/bill

{
	"userUuid":"42be74e6-dfc8-4406-8405-a273a4dd3bd2",
	"billName": "bill number two",
	"startSum":25.59,
	"description":"test-bill",
	"billNumber":6
}

#create category
localhost:8011/bills-service/category

{
	"userUuid":"42be74e6-dfc8-4406-8405-a273a4dd3bd2",
	"categoryName": "category number two",
	"type":"OUTCOME",
	"decryption":"test-category"
}
#create operation
localhost:8011/bills-service/operation

{
	"userUuid":"42be74e6-dfc8-4406-8405-a273a4dd3bd2",
	"billUuid":"13295df5-0adb-4aa9-926c-9ece8346d104",
	"categoryUuid":"a62e6979-adb9-44e7-bc15-e61f2d1cc570",
	"sum":4.0,
	"currency":"RUB",
	"type":"OUTCOME"
}

