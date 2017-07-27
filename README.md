# Models-Interfaces-Persistence-Services-WebApp Archetype [![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

A Maven archetype for a multi-module project, which includes model, interfaces, persistence, service and webapp modules

## Overview
This is a Maven archetype for creating a multi-module project including the following modules:


* Models
* Interfaces
* Persistence
* Services
* Webapp

### Models
The models module includes the business domain models

### Interfaces
The interfaces module defines interfaces to communicate each layer (this is an interface-based-architecture project), and might include custom exceptions and some utils classes.

### Persistence
The persistence module implements the logic to access and persist data.

### Services
The services module implements the services that the application will expose. This module should also implement the business logic of the application.

### Webapp
The webapp module implements the application controllers (REST endpoints), and is in charge of configurating all the project (through Spring Beans).


## Dependencies

* Servlet API
* Springframework
	* Spring core
	* Spring web
	* Spring context
	* Spring test
* Jersey Servlet Containers
* Jackson JSON provider
* SLF4J API
* Logback
	* Logback classing
	* Logback core
	* Logback Spring extension
* JCL over SLF4J
* JUnit
* Mockito



## Getting Started

These instructions will install the archetype into your local machine

### Prerequisites

1. Clone the repository or download source code:

	```
	$ git clone https://github.com/juanmbellini/model-interfaces-persistence-service-webapp-archetype.git
	```
	or
	
	```
	$ wget https://github.com/juanmbellini/model-interfaces-persistence-service-webapp-archetype/archive/master.zip
	```

2. Install Maven

	#### Mac OS X
	```
	$ brew install maven
	```
	
	#### Ubuntu
	```
	$ sudo apt-get install maven
	```
	
	#### Other OSes
	Check https://maven.apache.org/install.html.
	

### Build

1. Change working directory to ```<PROJECT-ROOT>/model-interfaces-persistence-service-webapp-archetype```

	```
	$ cd <PROJECT-ROOT>/model-interfaces-persistence-service-webapp-archetype
	```
	
2. Install the archetype

	```
	$ mvn clean install
	```

## Usage

1. Create a new project from a local archetype

	```
	$ mvn archetype:generate -DarchetypeCatalog=local
	```

2. Select the ```com.bellotapps.archetypes:model-interfaces-persistence-service-webapp-archetype``` option.

3.	Follow the instructions, setting ```groupId```, ```artifactId```, ```version``` and ```package```.

## License

Copyright 2017 BellotApps

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

## Author
* [Juan Marcos Bellini](https://github.com/juanmbellini)