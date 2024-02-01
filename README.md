# Crypto

A Crypto App, developed with Dagger-Hilt, Retrofit, Clean Architecture and used [coinpaprika.com](https://coinpaprika.com/) as API interface.

# Clean Architecture

## Data Layer

### Remote
Remotes has dto(Data Transfer Object) and API interface. Dto is used to convert API response to model. Remote provide the actual data from external sources(eg. APIs, databases).
They implement the repository interface defined in the domain layer.

### Repository
Repository havs the implementation of the repository contract defined in the domain layer. They interact with data sources to retrieve data.
Repository implementations allow switching data sources without modifying the domain layer.

## Domain Layer
### Model
Model represents the core data models of the application. They define the structure and properties of the data. Create an model to be passed around in the application.

### Repository
Repository defines the contract between the data layer and domain layer. Create a repository to specify the methods for data operations. They handle data operations.

### Usecase
Usecase represents the business logic of the applications.
They depend on the repository and call its methods to perform data operate.

## Presentation Layer
### ViewModel
ViewModel manages the state of the UI and business logic. It depends on usecases to execute business operations.
ViewModel emits states to the UI, decoupling it from business logic.

### Screen
Screens are responsible for displaying UI components and listening to ViewModel states.
They react to state changes and update the UI accordingly.

# Dependency Injection
Dependency injection is used to provide dependencies to different layer of the app.
It help in managing the dependencies and allows easy swapping of implementations. 
For example. Dagger-Hilt can be used for dependency injection.
