# Running

`./gradlew bootRun`

# Example requests

1. Create user
```
curl -u testuser:pass -X POST http://localhost:8080/v1/user \
    -H "Content-Type: application/json" \
    -d '{"firstName":"Donald","lastName":"Knuth","location":"NY"}'
```
2. Retrieve all users
```
 curl -u testuser:pass -X GET http://localhost:8080/v1/user
```
3. Retrieve user by id
```
curl -u testuser:pass -X GET http://localhost:8080/v1/user/1
```    
4. Retrieve user by location
```
curl -u testuser:pass -X GET "http://localhost:8080/v1/user?location=NY"
```
5. Update user location
```
curl -u testuser:pass -X PUT http://localhost:8080/v1/user/1 \
    -H "Content-Type: application/json" \
    -d '{"location":"LA"}'
```    
6. Delete user
```
curl -u testuser:pass -X DELETE http://localhost:8080/v1/user/1
```
