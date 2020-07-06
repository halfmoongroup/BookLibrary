# BookLibrary

### A trivial Bootique app that implements a "Book" repository. 

The purpose of this application is to provide a simple set of REST style endpoints that can be used with swagger to create clients that can use these functions.

To use the swagger openapi spec, you can either use the file included in the source code, 'BookLibrary.yaml' or you can start up the application and hit the swagger endpoint. 

To use the applicaiton you need a database set up in postgres, the name of the database should be 'info' the owner of the database should be a user with the name glorp and a password set to password. This database need only be available on your local workstation and 

The application should start up and run on port 8080 with the name exampel. 

### Technologies
- Bootique.io
- Apache Cayenne
- Postgres
- Jersey
- Swagger

