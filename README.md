# Zendesk
## Zendesk Coding Challenge - Software Engineering
Console Application that will let you see all the tickers for your account
 A Ticket Viewer that will:\
● Connect to the Zendesk API\
● Request all the tickets for your account\
● Display them in a list\
● Display individual ticket details\
● Page through tickets when more than 25 are returned

## Code style

[![js-standard-style](https://img.shields.io/badge/code%20style-standard-brightgreen.svg?style=flat)](https://github.com/feross/standard)
 

## Tech/framework used

**Built with** 
- [Spring](https://spring.io)
- JAVA 11 (JAVA 11 is required to set up on your path)
-  [Maven](https://maven.apache.org)
## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/Trance-raver/fetchRewardsDemo.git
```
**2. Run the app using maven (root directory)**

```bash
mvnw spring-boot:run
```
**3. Run the tests (root directory)**
```
mvn test
```
## Explore the application

User can view all the tickets or can view a single ticket as well

Tests are inclued in the Test folder ./ZendeskApplicationTests which mock Resttemplate connection.

I have used [ASCIITable](https://github.com/vdmeer/asciitable) to display data properly in a table format.



