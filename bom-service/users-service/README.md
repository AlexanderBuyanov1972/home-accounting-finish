# users-for-home-accounting-2ver

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
