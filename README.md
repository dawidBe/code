code_challange

A simple code to process a backend system like twitter.

REST METHODS:

POST/user/addNewUser - create new user with patrameter "name"

GET /user/list - get list of users

GET /user/{name} - get current user with name

PUT /user/{userName}/follow/{followerName} - user with userName will follow user followerName

PUT /user/{userName}/unFollow/{followerName} - user with userName will unFollow user followerName

POST /message/addMessage - add message for user. Parameter "userName" for name of user and "message" for message

GET /message/{userName} - get all message for current user with userName

GET /message/{userName}/follower - get all message that follows current user with userName

RUNNING:

It is using Spring Boot and gradle. You can run it from console, use: ./gradlew build && java -jar build/libs/code_challenge-0.1.jar
