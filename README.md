
TASK:
```
Java Developer Role – Practical Test
Requirement
Using any Java tools/framework, develop a backend to implement the following
Accept a JSON payload via HTTP POST with mandatory field validation - yep
Simple token based authorization - yes
Store the content in the database - yes
Audit the request - SLF4J
Integration test - basic tests
Simple demo using curl
If URL ends in ‘/nocontent’, return HTTP 204 - yep
If URL is ‘/echo’, return HTTP 200 and original payload - yep
If URL is ‘/device’, return HTTP 200 and only ‘DeviceId’ field - yep
Else return HTTP 400 - yep
Sample payload
{
   "RecordType": "xxx",
   "DeviceId": "357370040159770",
   "EventDateTime": "2014-05-12T05:09:48Z",
   "FieldA": 68,
   "FieldB": "xxx",
   "FieldC": 123.45
}
```


Completed with Spring boot

RecordController takes in records like the sample payload regardless of capitalisation however all fields must not be null

All routes will kick back a 401 with out the jwt token. Use localhost:8080/login?user=something&password=something to get the token 

The user and password are not checked so put whatever 

Contents are stored in a JPA repository that is connected to a MySQL DB, I have it set to use root/password as user credentials as I dont keep mysql running on my local machine

Posts are logged with SLF4J 

Basic expected routes are tested in src/test/java/com/example/teletracTest/TeletracTestApplicationTests.java

The tests are the simple commands

Run with "mvn spring-boot:run"

hit login to get bearer token 

POST /echo, /device or /nocontent to add records

GET / and /{id} exist to prove its storing 

use DELETE /{id} or /all to clean up 
