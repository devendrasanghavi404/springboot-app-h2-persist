# springboot-app-h2-persist
example app

# ------application.yml----------
spring:
  devtools:
    restart:
      enabled: true
    livereload:
      enabled: true
  datasource:
    url: jdbc:h2:mem:webapp
    username: sa
    password:
    driver-class-name: org.h2.Driver
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    generate-ddl: true
    hibernate:
      ddl-auto: update
  h2:
    console:
      enabled: true
      path: /h2-console
  output:
    ansi:
      enabled: ALWAYS
  sql:
    init:
      data-locations: classpath:data/h2-database/employees.sql
      mode: always
      
# ---------POM.xml-dependencies-----------

 <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.0.4</version>
        </dependency>
    </dependencies>

# -----------Folder Structure ------------------
Volume serial number is 88E3-E7F4
C:.
│   .gitignore
│   boot-example-webapp.iml
│   folderStructure.txt
│   HELP.md
│   mvnw
│   mvnw.cmd
│   pom.xml
│
├───.mvn
│   └───wrapper
│           maven-wrapper.jar
│           maven-wrapper.properties
│
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───example
│   │   │           │   BootExampleWebappApplication.java
│   │   │           │   ServletInitializer.java
│   │   │           │
│   │   │           ├───config
│   │   │           ├───controller
│   │   │           │       EmployeeController.java
│   │   │           │
│   │   │           ├───dto
│   │   │           │       EmployeeDTO.java
│   │   │           │
│   │   │           ├───helper
│   │   │           ├───mapper
│   │   │           ├───model
│   │   │           │       Employee.java
│   │   │           │
│   │   │           ├───repository
│   │   │           │       EmployeeRepository.java
│   │   │           │
│   │   │           ├───service
│   │   │           │   └───impl
│   │   │           │           EmployeeService.java
│   │   │           │
│   │   │           └───util
│   │   └───resources
│   │       │   application.properties
│   │       │   application.yml
│   │       │   h2db.mv.db
│   │       │   h2db.trace.db
│   │       │   webapp.xml.mv.db
│   │       │   webapp.xml.trace.db
│   │       │
│   │       ├───data
│   │       │   │   EmployeeList.json
│   │       │   │
│   │       │   └───h2-database
│   │       │           employees.sql
│   │       │
│   │       ├───static
│   │       └───templates
│   └───test
│       └───java
│           └───com
│               └───example
│                       BootExampleWebappApplicationTests.java
│
└───target
    ├───classes
    │   │   application.properties
    │   │   application.yml
    │   │   h2db.mv.db
    │   │   h2db.trace.db
    │   │   webapp.xml.mv.db
    │   │   webapp.xml.trace.db
    │   │
    │   ├───com
    │   │   └───example
    │   │       │   BootExampleWebappApplication.class
    │   │       │   ServletInitializer.class
    │   │       │
    │   │       ├───controller
    │   │       │       EmployeeController.class
    │   │       │
    │   │       ├───dto
    │   │       │       EmployeeDTO$EmployeeDTOBuilder.class
    │   │       │       EmployeeDTO.class
    │   │       │
    │   │       ├───model
    │   │       │       Employee$EmployeeBuilder.class
    │   │       │       Employee.class
    │   │       │
    │   │       ├───repository
    │   │       │       EmployeeRepository.class
    │   │       │
    │   │       └───service
    │   │           └───impl
    │   │                   EmployeeService.class
    │   │
    │   └───data
    │       │   EmployeeList.json
    │       │
    │       └───h2-database
    │               employees.sql
    │
    └───generated-sources
        └───annotations
