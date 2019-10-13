# blog-category-api
Api to manage the categories for the blog application

The API documentation is generated with ** Swagger ** and can be found at the following URL when the program starts.
```
http://localhost:8201/swagger-ui.html#/
```

## COMPILE

Execution steps
	mvn clean package -DskipTests	=> Only compile the code
	mvn test -Dtest=!CucumberTest	=> Only Junit tests
	mvn test -Dtest=CucumberTest	=> Only cucumber tests

## CHANGELOG

###1.0.0
	Create endpoint to:
		- create category
		- update category
		- delete category
		- find category by code
		- find all categories