# Context

**A Proof of Concept (POC)** is typically a small project created to test whether a certain idea or approach is feasible. It’s not intended to be a final product but rather a prototype to demonstrate and validate key concepts and functionalities. Here applied to a **Product Lifecycle Management (PLM)** software editor: collaborative product design solution (satellites, aircraft, car, clothing, robots, ...). Product conception requires to manage its data.

# Business
- **Part:** represents a physical element or set of physical elements. For instance, a car (product) is composed, among others, of 4 wheels (4 occurrences of the Part "Wheel"). The wheel it self is composed by 1 wheel rim (1 occurrence of the Part "Rim"), 1 tyre (1 occurrence of the Part "Tyre") and X nuts (X occurrences of the Part "Nuts")

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

**Timeframe:** 72h

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





# Improvements

|   | Expected  | Concept | Implementation                                                                                                                                                                                                                                                                                                                                                                                                                                        |
|---|---|---|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1 | Data Integrity | Data associated with parts and their related entities remain accurate and consistent.  | <ul><li> Validate changes to parts before saving, ensuring all linked documents are updated accordingly. </li></ul>                                                                                                                                                                                                                                                                                                                                   |
| 2 | Handling Data Quality Bugs |  Address common issues where data quality bugs occur, often in non-reproducible ways outside of production. | <ul><li>Simplify error handling and logging mechanisms to capture data quality issues effectively. </li></ul>                                                                                                                                                                                                                                                                                                                                         |
| 3 | Performance Optimization | Enhance performance (time and memory) while keeping the code simple, without compromising readability and maintainability.  | <ul><li>Focus on optimizing database queries and minimizing unnecessary processing.</li></ul>                                                                                                                                                                                                                                                                                                                                                         |
| 4 | Global Customer Base |  Ensure the system supports users from around the world. | <ul><li>Implement localization and internationalization best practices.</li></ul>                                                                                                                                                                                                                                                                                                                                                                     |
| 5 | Future Entity Additions |  Make the system adaptable to future changes, such as adding new entity types (other than Part and Document) with different behaviors (other than reservation, life cycle, versioning) | <ul><li>Implement Object-Oriented Principles</li><li>Use interfaces and abstract classes to allow easy integration of new entities without major code changes.</li><li>Use inheritance and polymorphism to manage parts and their relationships.</li><li>Create base classes and interfaces for common behaviors.</li><li>Use design patterns like Factory, Singleton, and Observer.</li></ul>                                                        |
| 6 | Service Reusability |  Make services offered by the solution reusable, flexible, maintainable and customizable for other developers. | <ul><li>Create small modular reusable service components with well-defined public APIs and single responsability.</li><li>Documentation: Adding comments improves the understandability and maintainability of the code, which is beneficial for other developers who might customize the solution. Readability: Clean and well-structured code enhances readability, making it easier for developers to work with and reuse the services..</li></ul> |


# Not Expected and Not Implemented Improvements

1. **User Management**: No implementation details for user creation and login mechanisms.
2. **Web Security**: No security-related configurations or features.
3. **Spring Configuration**: Data sources, Hibernate settings and Spring configuration should remain unchanged.
4. **REST Services Input/Output**: Current controller methods and input/output signature should remain unchanged.
5. **Build Tools**: No Maven or Gradle integration or setup required.

# Author
[Vladimir Davidov ](https://github.com/v-dav)