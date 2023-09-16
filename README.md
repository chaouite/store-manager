# ILEA Store Manager Application

The **ILEA Store Manager Application** is a simple web application built using Spring Boot MVC in combination with thymeleaf for managing items in a store's inventory.

## Table of Contents

- [Getting Started](#getting-started)
- [Features](#features)
- [Dependencies](#dependencies)
- [Overview](#overview)

## Getting Started

### Prerequisites

Before running the application, ensure you have the following prerequisites installed:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)

### Installation

1. **Clone the repository:**

   ```shell
   git clone https://github.com/chaouite/store-manager.git
   
2. **Build the project using Maven:**

   ```shell
   cd store-manager
   mvn clean install

3. Run the application:

    ```shell
    mvn clean spring-boot:run
   
### Accessing the Application

The application should now be running locally at http://localhost:8080.   
   

## Features

* Three layers codebase
* Add new items to the inventory.
* Update existing items in the inventory.
* View the list of items in the inventory.
* Navigate between Form page and Inventory page
* Notify user: success / failure
* Prevent updates if the new order date has a 3-day discrepancy from the previous one.
* Prevent invalid inputs such as future dates, special characters, blank entries, missing categories, negative values, and discounts greater than the price ...
* Unit tests
* Integration tests

## Dependencies

* Spring Boot Starter Web
* Spring Boot DevTools
* Spring Boot Starter Test
* Spring Boot Starter Thymeleaf
* Spring Boot Starter Validation
* Junit

## Overview

### Form overview

![form.html](/images/Form.png "This is a sample image.")

### Inventory overview

![inventory.html](/images/Inventory.png "This is a sample image.")


