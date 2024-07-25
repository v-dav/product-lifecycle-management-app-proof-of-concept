# Context

![image](https://github.com/user-attachments/assets/0becb34c-adb3-4b48-9ebe-b2efde34832d)


**A Proof of Concept (POC)** is typically a small project created to test whether a certain idea or approach is feasible. It’s not intended to be a final product but rather a prototype to demonstrate and validate key concepts and functionalities. Here applied to a **Product Lifecycle Management (PLM)** software editor: collaborative product design solution (satellites, aircraft, car, clothing, robots, ...). Product conception requires to manage its data.

# Business logic
- **Part:** represents a physical element or set of physical elements. For instance, a car (product) is composed, among others, of 4 wheels (4 occurrences of the Part "Wheel"). The wheel it self is composed by 1 wheel rim (1 occurrence of the Part "Rim"), 1 tyre (1 occurrence of the Part "Tyre") and X nuts (X occurrences of the Part "Nuts")
  <br></br>
- **Document:** represents the documentation (Studies, business requirements, tests, maintenance, ...). A Document can be linked to a Part, in such case both share the same life cycle template and all actions applied to the part is automatically applied to all linked documents 
# Key Points

1. **Part-Centric Approach**:
    - Each part is an object with properties (metadata) such as name, version, state, etc.
    - Parts are linked to documents.
    - Parent-child relationships exist between parts, managing hierarchical structures.
<br></br>
2. **Services**
(operations) that can be performed on parts:
    - **Reservation**: In a collaborative context, modifying an entity requires to reserve it as first to avoid concurrent updates.
    - **Lifecycle Management**: All entity evolve over time, it goes through different steps named **lifecycle states**. Each entity has a defined set of lifecycle states which is named the **lifecycle template**. For exemple a design Part freshly created by a designer starts in the "In progress" state. Once the designer job is done, it must be validated by other people and so the Part goes in the "Under validation" state. When the design, of the Part, has been validated, it goes into the final state, "Release".
    - **Versioning**: An entity in its final life cycle state can restart a new cycle by creating a new version if some modifications are required. The first version is not lost and still exist.
      <br></br>

3. **Rules** governing how parts can be manipulated, ensuring data integrity and preventing conflicting changes:
    - **Only reserved parts can be modified:** entity can only be updated if reserved and only by the user who reserved them.
    - **Prevent lifecycle state changes on reserved parts:** Entity's lifecycle state cannot be modified if reserved.
    - **Final state management:** Entity in its final lifecycle state cannot be reserved and its lifecycle state cannot be modified.
    - **Transition to new versions only from finalized parts:** Entity new version can be created only if the current version is in its final lifecycle state.
    - **Service Appliance:** A service cannot be applied to a document linked to a Part, services must be launched from the Part and are automatically applied to all linked documents
    - **Rule Violation Notifications:** if a service cannot be executed due to one of previous rules, the invoker must be notified with an explicit reason.

# Mission

**Industrialization.** In other words taking an existing POC Java codebase that serves as a prototype and **refactoring** it to be more modular, rich and maintainable while following specific guidelines.

**Timeframe:** 48h

**Stack:**
- Java (language)
- Spring (development framework)
- Hibernate (ORM framework)

**Focus**
- Code clarity
- Adherance to specifications


# Deliverables

1.	**Java Source Code**:
	- Refactored for simplicity and clarity.
	- Adheres to object-oriented principles and best practices.

2.	**Documentation**:
	- Detailed explanation of each expected improvement.
	- Includes architectural decisions and rationale for changes.

      

# Expected Improvements

1. **Data Integrity:** Data integrity is essential (this includes entity and their related entities)
2. **Handling Data Quality Bugs and Issues:** Despite this, bugs related to data quality is very common (i.e. often bugs are not reproducible outside of the PROD)
3. **Performances**: must be optimals but this no real time software (i.e. code simplicity > micro optimisation)
4. **Future Entity Additions:** Some new entities will be added soon, different than Part and Document but with the same behaviors but NOT necessarily all of them (i.e. rÈservation/life cycle/versionning)
5. **Global Customer Base:** We have customers all arround the world, and so are their users too
6. **Service Reusability:** Integration (i.e. solution customisation to fit customer specific needs) maybe done will by others companies. Services offered by the solution must be easy to reuse for developpers.

# Implemented Improvements

1. **Ensuring Data Integrity:**
   - Added **validation annotations** (@NotBlank, @Min) to enforce constraints at the **field** level.
   - Implemented **validation logic** in the **setters** to prevent setting invalid values (null or empty strings or null objects).
   - **Argumentation**: added validation annotations to enforce constraints on the fields, using the Java Bean Validation (JSR 380) annotations provided by Hibernate Validator (which is included in Spring Boot).  @NotBlank on reference and version to ensure these fields are not blank. @Min on iteration to ensure it is a positive integer. These annotations help ensure that the data being persisted conforms to certain rules, thereby maintaining data integrity. Each setter that takes a reference type argument (including String) checks if it’s not null or contains only whitespaces in case of strings and throws an **IllegalArgumentException** if it is. This **validation logic** ensures that encapsulated private fields cannot be set to invalid values even after the object is created. Added hibernate-validator and validation-api to the dependancies to handle @NotBlank and Min.
   <br></br>
2. **Handling Data Quality Bugs and Issues:**
   - **Exception Handling**
       - Added validation and exception handling in setter methods.
       - Used SLF4J to log errors when invalid values are set.
   - **Logging: Added SLF4J and SLF4J Simple Implementation**:
       - Ensured proper SLF4J logging with a simple logging backend.
       - Configured and tested logging in the Part class and Main test class.
   - **Argumentation**: used a logging framework SLF4J to log important events and errors to ensure that exceptions are caught and logged properly. Ensuring that exceptions are caught and logged properly. Si I imported SLF4J for logging and added logging statements in setters to log errors when invalid data is attempted to be set, before throwing exceptions. I added also slf4j-simple dependancy to handle this. By default, the logging output will be directed to the console (standard output), not a file. If we want to log messages to a file, we will need to use a logging framework that supports file logging, such as Logback or Log4J.
   <br></br>
3. **Performances**:
   - Kept code **clean, simple, readable and maintainable**
   - Avoid **redundant** validations
     <br></br>
4. **Future Entity Additions:**
   - Defined an **Interfaces** that define common behaviours
   - Created an **Abstract Base Classes** that implements the interface and provides common properties and methods.
   - Extended the **Base Class in Specific Entities, Services, Dao and Controllers**: Modified existing classes to extend the base classes, ensuring they inherit the common behaviors and keep proper individual behaviours and fields.
   - **Argumentation**: To address the requirement of adding new entities with similar behaviors but not necessarily all of them, we can use **inheritance** and **interfaces** to create a flexible and reusable design. This approach allows us to define common behaviors in a base class or interface and then extend or implement them in specific entities making the system adaptable to future changes
     <br></br>
5. **Global Customer Base:**
   - **Resource Bundle Usage**: All messages are fetched from the external resource bundles to support multiple languages (e.g., .properties files). They are in src/resources folder.
   - **Localized Error and Logger Messages**: Both validation error messages and logger error messages are localized, meaning that they are displayed in the appropriate language based on the user’s locale. If we change `Locale.FRENCH` or Locale.ENGLISH we will have the logged messages and errors in FRENCH or ENGLISH.
   - **Argumentation**: We need to ensure that our application is designed with internationalization (i18n) and localization (l10n) in mind. This means our application should support multiple languages and regional formats, and be flexible enough to adapt to various cultural contexts.
     <br></br>
6. **Service Integration and Reusability:**
    - **Added Detailed JavaDoc Comments**: Added comprehensive JavaDoc comments for the classes and each method, providing clear explanations of their purpose and usage. This documentation helps other developers understand the code’s functionality and how to integrate and extend it.
    - - Using Object-Oriented Programming **Inheritance** and **Interface** concepts with following benefits:
      - **Reusability**: New entities can extend AbstractEntity to inherit common behaviors, reducing code duplication.
      - **Flexibility**: Entities can override methods from the base class to provide specific implementations if needed.
      - **Maintainability**: Centralizing common behaviors in a base class makes the code easier to maintain and extend.
      
# Not Expected and Not Implemented Improvements

1. **User Management**: No implementation details for user creation and login mechanisms.
2. **Web Security**: No security-related configurations or features.
3. **Spring Configuration**: Data sources, Hibernate settings and Spring configuration should remain unchanged.
4. **REST Services Input/Output**: Current controller methods and input/output signature should remain unchanged.
5. **Build Tools**: No Maven or Gradle integration or setup required.

# Notes

- Also included:
    - **resource/** folder with bundles for internationalization,
    - **lib/** folder with 3 additional dependancies for logging and validation annotations
        - hibernate-validator-8.0.1.Final.jar
        - slf4j-simple-1.7.30.jar
        - validation-api-2.0.1.Final.jar
    - **src/test/Main.java** file: file that allowed me to test locally implemented improvements to the POC. I compiled and executed as follows:

  ```javac -cp lib/hibernate-commons-annotations-5.1.0.Final.jar:lib/hibernate-validator-8.0.1.Final.jar:lib/validation-api-2.0.1.Final.jar:lib/hibernate-core-5.3.7.Final.jar:lib/hibernate-jpa-2.1-api-1.0.2.Final.jar:lib/slf4j-api-1.7.28.jar:lib/spring-beans-5.1.7.RELEASE.jar:lib/spring-boot-2.1.5.RELEASE.jar:lib/spring-boot-autoconfigure-2.1.5.RELEASE.jar:lib/spring-context-5.1.7.RELEASE.jar:lib/spring-core-5.1.7.RELEASE.jar:lib/spring-security-web-5.1.5.RELEASE.jar:lib/spring-tx-5.1.7.RELEASE.jar:lib/spring-web-5.1.7.RELEASE.jar:lib/spring-webmvc-5.1.7.RELEASE.jar:lib/slf4j-simple-1.7.30.jar:resources -d bin src/plm/model/*.java src/plm/test/Main.java```
  <br></br>
  ```java -cp lib/hibernate-commons-annotations-5.1.0.Final.jar:lib/hibernate-validator-8.0.1.Final.jar:lib/validation-api-2.0.1.Final.jar:lib/hibernate-core-5.3.7.Final.jar:lib/hibernate-jpa-2.1-api-1.0.2.Final.jar:lib/slf4j-api-1.7.28.jar:lib/spring-beans-5.1.7.RELEASE.jar:lib/spring-boot-2.1.5.RELEASE.jar:lib/spring-boot-autoconfigure-2.1.5.RELEASE.jar:lib/spring-context-5.1.7.RELEASE.jar:lib/spring-core-5.1.7.RELEASE.jar:lib/spring-security-web-5.1.5.RELEASE.jar:lib/spring-tx-5.1.7.RELEASE.jar:lib/spring-web-5.1.7.RELEASE.jar:lib/spring-webmvc-5.1.7.RELEASE.jar:lib/slf4j-simple-1.7.30.jar:resources:bin plm.test.Main```
  <br></br>
- Inheritance and Interfaces are used for Entities, Services, Controllers and Dao in case of new Entities (other than Part and Documents) are added in the future which implies that each new Entity will have it’s own Controller, Service and Dao. It allows better modularity and feature extensions and consistency.
- I noticed that API endpoint for **setState** method in **Controllers** was **“/Part/free”,** same as for **free** method. Since the requirement was that current controller methods and input/output signature should remain unchanged, I left it as is.
- All the logic rules are respected

# Author
[Vladimir Davidov ](https://github.com/v-dav)
