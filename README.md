# Bookstore-Tieto
Given:
Customer: Bookstore.com
Need to implement Java based web application where 
1. an admin user can add a new book,
2. main index page can show all existing books.
a. Could be organized in pages, but there should be visible at least 50 books 
per page
b. Or you may choose additional content loading while user scrolls at bottom of 
page 
3. Newest books are located at top of the page.
4. Main page load time cannot exceed 2 seconds.
5. System cannot store a new book which name already exists.
6. It is forecasted 10 parallel user sessions per second, during workdays from 11:00 to 
15:00.

Delivery:
1. Please describe / draw main components of web application, how would you build the 
system. There are no strict requirements to UI, as well you may include minimum amount of 
data/fields to be requested from end-users and stored in DB. Please describe a reason of 
choosing an architecture, technologies, frameworks, etc. You may draw by hand and take a 
photo, e.g. its not required to use tools for UML, dataflow, MS Office, etc.

..............

Main Components and System Architecture Based on the given requirements, I would recommend using the following components and system architecture to build the web application:
•	Java 11: The latest version of Java offers better performance, security, and stability.
•	Spring Boot: A popular framework that provides a simplified way to build web applications and microservices.
•	Spring MVC: A framework that supports the Model-View-Controller pattern and provides a structured approach to building web applications.
•	Thymeleaf: A modern server-side Java template engine that enables the creation of highly maintainable and testable HTML templates.
•	H2 database: A popular open-source relational database management system that is typically faster than MySQL when dealing with small to medium-sized databases
The system architecture would be based on the following components:
•	Presentation Layer: This layer would be responsible for handling user requests and rendering views. The Spring MVC framework would be used to implement this layer. The Thymeleaf template engine would be used to render HTML templates.
•	Business Layer: This layer would be responsible for handling business logic and interactions with the data access layer. The Spring Boot framework would be used to implement this layer.
•	Data Access Layer: This layer would be responsible for handling interactions with the MySQL database. The Spring Data framework would be used to implement this layer.
The overall architecture of the system can be represented as follows:

..............

2. Describe risks (if any) you would see in your chosen solution.
Some potential risks of this solution include:
•	Performance: Since the main page load time cannot exceed 2 seconds, the system must be designed to handle a large number of requests and serve the content quickly. If the system is not optimized for performance, it may lead to slow page load times and a poor user experience.
•	Scalability: The system must be designed to handle a large number of parallel user sessions during peak hours. If the system is not scalable, it may lead to downtime or slow response times.
•	Security: The system must be designed to handle user data securely and prevent unauthorized access. If the system is not secure, it may lead to data breaches or other security vulnerabilities.

3. Provide source code and pre-compiled package.
4. Provide “short” setup guide, e.g. what is target OS, what to install (appserver, DB, …), 
how to deploy application packages and how to run all services so the application would be 
up-and-running at local environment and accessible in browser.
..............................
Here is a brief setup guide for the web application:
Target Operating System:
This setup guide assumes that you are using a Windows or Unix-based operating system.

What to install:
Java Development Kit (JDK) version 11 or later
Apache Maven
An IDE such as IntelliJ or Eclipse
H2 database (already included as a dependency in the project)
A web browser such as Chrome, Firefox or Safari

Deployment:
1.Clone or download the project source code from a repository to your local machine.
2.Compile and build the application using Maven. This will compile the application code, run the tests, and create a deployable package (a JAR file) under the target directory.
3.Start the H2 database and run the application by running the BookstoreApplication.java class. This will create the necessary tables in the database, populate them with sample data, and start the embedded web server to serve the application.
Open a web browser and navigate to http://localhost:8080/. The Bookstore.com application should be up-and-running and accessible in the browser.
4.Once the application is running, you can access it in your browser by visiting the following URL: http://localhost:8080/ 

..................

(Optional) 2. Given:
Prerequisites: completed task 8 with included source codes & installed environment
New change request to the Bookstore application.
A subcontractor is building an e-commerce system where one of sales channels is going to 
provide books for sale. It was decided that our Bookstore system will manage information 
about books including price, but the e-commerce system will take care of stocks and 
payments.
Please update the application so 
1. it’s possible to add a price to a book
2. add some unit tests
