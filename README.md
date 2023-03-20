/*
The Pizza project is the web application that creates CRUD (create, read, update, delete) records for two entitites: pizza and cafe. 
There is one to many dependency in it, which means that one pizza may be binded with only one cafe, while cafe can be binded to several pizzas.

The project consists of four layers: entity, repository, service and controller.

Pizza entity includes such information about pizza as name, size, key ingridients, price, cafe id, where this pizza is binded to.
Cafe entity includes: name, working hours, location, phone, mail and pizzas that exists there. 

Repository stores in two different tables the data about pizzas and cafes in SQL database, which is realized via H2. 

Service is responsible for connecting repository and controllers, while the controller allows users to send the requests to this application.

The web project is secured for Post, Delete and Update request. Only admin users can do such requests. 
Others, admins and non-authentificated users are able to make Get requests.

Also the program has the validation for entity fields. 
*/
