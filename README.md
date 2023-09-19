# CodeChallenge-QuoteApi
Test Api to display my software developer knoledge using SpringBoot and MongoDb

## Startup 

### Using docker-compose.
Launches the api with a connected local MongoDB database with the initial values stored in [quotes.json](/mongo-seed/quotes.json) file

```
docker-compose build
```

```
docker-compose up
```

### Using docker
Only executes the api for connecting to external MongoDB databases

```
docker build -t quoteapi .
```

```
docker run -it quoteapi -e MONGODB_DATABASE='Quotes' -e MONGODB_HOST='localhost' -e MONGODB_PORT="27017"
```

## Querring

### Get All Quotes

```
curl --location --request GET 'http://localhost:8080/QuoteGarden/get-quotes' \
--header 'Content-Type: application/json' \
--data '{}'
```


### Get Quotes By Filter 

```
curl --location --request GET 'http://localhost:8080/QuoteGarden/get-quotes' \
--header 'Content-Type: application/json' \
--data '{
    "id" : "5eb17aaeb69dc744b4e72a4a",
    "author" : "Bill Gates"
}'
```