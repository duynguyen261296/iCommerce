# iCommerce

iCommerce is a RESTful Web Services application to support an iCommerce website.

## Features

- Provide APIs for administrator to Add/Update/Delete product.
- Provide APIs for customer to shows all product which the customer can filter, sort and search
for products based on different criteria such as name, price, brand,...
- API for customer to order product(s) that he/she want to buy. The customer needs to input full name
phone number, address, email to submit an order.

## Technologies

iCommerce uses a number of open source projects to work properly:

- [Java 8](https://java.com/en/download/help/java8.html) - programming language for this application 
- [Spring boot](https://spring.io/projects/spring-boot) - makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) - makes it easy to easily implement JPA based repositories.
- [JPA/Hibernate](https://hibernate.org/) - makes it easy to working with persistence layer
- [Spring Security](https://spring.io/projects/spring-security) - framework that focuses on providing both authentication and authorization to Java applications.
- [JUnit](https://junit.org/junit5/) - makes it ease to perform unit testing
- [H2 Database](https://www.h2database.com/html/main.html) - database use for this application
- And of course iCommerce itself is open source with a [public repository](https://github.com/duynguyen261296/iCommerce) on GitHub.

## Installation and Launch

Before you continue, ensure you meet the following requirements:

* You have installed JDK 8 or higher.
* You have installed Maven (if not may be you can't run command 'mvn')
* You are using Windows or Linux or Mac OS machine.
* You have a basic understanding some of framework above.
* iCommerce  requires Java 8 or higher to run.
* All version of framework was define in pom.xml
* You should use [Postman](https://www.postman.com/downloads/) to test APIs with method POST/UPDATE/DELETE

Install the dependencies and start the server.

```sh
- Go to the root of project and run command line following:
"mvn clean install" - to compile and download library of framework
"mvn spring-boot:run" - to start server
```

## How to use

### 1) Login/Logout
When you already started the spring boot server go to **[localhost:8080](localhost:8080)** to login this application. I created 3 User in database to use, I'm not yet implement API to create new User in this version may be implement later, so please use one of Users in below.
```sh
- The information of user following:
"user_1" - "password1" - "ROLE_USER"
"user_2" - "password2" - "ROLE_USER"
"admin_1" -"password3" - "ROLE_ADMIN"
```
*Note: If you login with role user and try to access some APIs of user with role admin you will get 403 Forbidden Error.
### 2) Use APIs for PRODUCT
#### - When you login with role user, you just only can use API with method "Get" for product, the APIs following:
- **[localhost:8080/products](localhost:8080/products)** : use to get all products

- **[localhost:8080/products/sort?increase=true](localhost:8080/products/sort?increase=true)** : use to sort the products base on price, this API has one param if param "increase" is true the products will be arrange with price ascending and opposite.

- **[localhost:8080/products/search?params=?](localhost:8080/products/search)**: use to search products with criteria, we have 4 param [name], [brand], [minPrice], [maxPrice] to build criteria. With [name] the API can filer a product has same name with param. With [brand] the API can filter all products has same brand. With[minPrice] the API can filter all products with price greater than or equal min price and with[maxPrice] filter all products with price less than or equal max price. For example: **[localhost:8080/products/search?name=Iphone12](localhost:8080/products/search?name=Iphone12)** to find detail aobut Iphone 12 or use **[localhost:8080/products/search?brand=Samsung&minPrice=1200](localhost:8080/products/search?brand=Samsung&minPrice=1200)** to find all smartphone of Samsung brand with minimum price is 1200.

#### - When you login with role admin, you can use API for all method for product, you can use [Postman](https://www.postman.com/downloads/) or other tool to test this APIs, the APIs following:
- **[localhost:8080/products/add](localhost:8080/products/add)** : use to add new a product

- **[localhost:8080/products/update](localhost:8080/products/update)** : use to update a product

- **[localhost:8080/products/delete](localhost:8080/products/delete)** : use to delete a product

### 3) Use APIs for ORDER
#### - User can order product that he/she want to buy, the APIs following:
- **[localhost:8080/orders](localhost:8080/orders)** : use to add new a order user want to buy, as a POST method so you can you [Postman](https://www.postman.com/downloads/) to test this API, when user order successfully, this API will calculate sum money have to pay and update the remaining quantity of Product.

- **[localhost:8080/orders/search?email=?](localhost:8080/orders/search?email=?)** : use to search history of orders of user by  user email.

### 4) Use Postman test APIs
#### - If you are not yet know about Postman, please follow this [link](https://www.youtube.com/watch?v=t5n07Ybz7yI) to setup and use this tool for testing.

## Sources
### The technology websites to learn and build this application following:
#### - [https://www.baeldung.com](https://www.baeldung.com/)
#### - [https://dzone.com](https://dzone.com)
#### - [https://mkyong.com](https://mkyong.com)


