Order Processing APIs (CRUD Operations) using the below Tech Stacks
1) Spring Boot
2) Java 
3) MongoDB
4) ActiveMQ
5) Basic Web Authentication using spring-security
6) JUnit Test case implementation

Below are the features implemented as part of OrderProcessing APIs
1) Order Creation using POST API - Create order JSON and publish to ActiveMQ as JSON object
2) ActiveMQ will consume JSON message and create order.
3) Direct API implementation also available to create orders directly (with JSON object)
4) Order created in the DB using MongoRepository
5) Update Order Status using PUT API - Create message in ActiveMQ to change order status
6) ActiveMQ consume the message and change the order status based on order number
7) GET API to retrieve the order details
8) DELETE API to delete the order
9) All the APIs secured using Basic spring-boot-starter-security
10) JUnit Test implementation for all the CRUD operations
