MadarApp - Clean Architecture User Manager
This is an Android project developed to demonstrate a robust, offline-first application using Clean Architecture and MVVM. The core objective was to build a scalable system where business logic remains entirely decoupled from both the UI framework and the database implementation.

Project Structure
The application is split into three specific modules to enforce a strict separation of concerns:

:domain: A pure Kotlin/JVM module. It contains the business entities, Use Cases, and a custom UserValidator. This module has zero dependencies on the Android framework.

:data: This module manages data persistence. It implements the Repository interfaces, handles the Room Database configuration, and includes Mappers to transform database models into domain entities.

:app: The presentation layer. Built using Material Design, ViewBinding, and DataBinding. It utilizes Hilt for dependency injection and manages UI state through ViewModels and LiveData.

Technical Stack
Language: Kotlin

Dependency Injection: Hilt (Dagger)

Database: Room Persistence Library

Architecture: MVVM + Clean Architecture

Jetpack Components: ViewModel, LiveData, and Lifecycle

Testing: JUnit 4 and MockK for unit testing

Testing Approach
The project follows the testing pyramid strategy to ensure reliability:

Domain Tests: Use Cases and validation logic are tested in isolation using pure JUnit in the JVM module.

Data Tests: Verification that Mappers correctly transform data without loss and that the Repository coordinates correctly between data sources.

UI Tests: ViewModels are unit tested using UnconfinedTestDispatcher and InstantTaskExecutorRule to verify state changes and LiveData emissions.

Architecture Decisions
Centralized Validation: Validation logic is placed in the Domain layer via the UserValidator class. This ensures business rules are consistent and testable without Android dependencies.

Data Mapping: Dedicated Mapper objects are used to ensure that Room-specific annotations and structures do not leak into the business logic layer.

Navigation Management: The navigation flow between Input and List screens is handled to ensure a clean backstack, preventing circular activity loops.
