# receipt-demo

solution of the test task of the company Clevertec


### To run the application do the following:

- download the project from the github repository [receipt-demo](https://github.com/dyskop/receipt-demo.git);
- open the project in `IDE` and build using `gradle build -x test` terminal command;
- run the application in docker-container using `docker-compose up -d` terminal command;
- take a look at the UML class diagram `diagram/uml-class-diagram.drawio.png`;
- import the collection of requests from the `doc` directory into `postman HTTP-client` and test the following endpoints of the rest-service using them:

    * `http://localhost:8080/orders` - the `post` method `create` of the `OrderController`: creates and persists into the database the `order` entity with the attributes passed in the `request body`, as well as the `item` and `receipt` entities, with the calculation of the total cost of the order, taking into promotions;
    * `http://localhost:8080/orders` - the `get` method `getAll` of the `OrderController`: returns a list of all `order` entities from the database;
    * `http://localhost:8080/orders/{id}` - the `get` method `getById` of the `OrderController`: returns an `order` entity with the given `id` from the database;
    * `http://localhost:8080/receipts/search?productId=5&card=1&productId=7&productId=7` - the `get` method `getByProductsAndCard` of the `ReceiptController`: returns a list of `receipt` entities from the database with the corresponding set of products and a discount card;
    * `http://localhost:8080/receipts` - the `get` method `getAll` of the `ReceiptController`: returns a list of all `receipt` entities from the database;
    * `http://localhost:8080/receipts/{id}` - the `get` method `getById` of the `ReceiptController`: returns a `receipt` entity with the given `id` from the database;
    * `http://localhost:8080/products` - the `post` method `create` of the `ProductController`: creates a `prodact` entity;
    * `http://localhost:8080/products` - the `get` method `getAll` of the `ProductController`: returns a list of all `prodact` entities;
    * `http://localhost:8080/products/{id}` - the `get` method `getById` of the `ProductController`: returns a `product` entity by `id` from the database;
    * `http://localhost:8080/products/{id}` - the `put` method `update` of the `ProductController`: updates a `prodact` entity by `id`;
    * `http://localhost:8080/products/{id}` - the `delete` method `deleteById` of the `ProductController`: removes a `prodact` entity by `id`;
    
- end the application with the `docker-compose down` terminal command;
- clean up docker resources with the `docker volume rm receipt-demo_db-data` and `docker image rm receipt-demo-app` terminal commands.

### Main technologies of the project:

- Java 17
- Spring-Boot
- Spring-Web
- Spring-Data-Jpa
- PostgreSQL
- Gradle
- Docker
- Spock Framework
- Git
