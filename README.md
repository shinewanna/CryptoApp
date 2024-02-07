# Crypto

A Crypto App, developed with Dagger-Hilt, Retrofit, Clean Architecture and used [coinpaprika.com](https://coinpaprika.com/) as API interface.

# Clean Architecture

## Data Layer

### Remote
Remotes has dto(Data Transfer Object) and API interface. Dto is used to convert API response to model. Remote provide the actual data from external sources(eg. APIs, databases).
They implement the repository interface defined in the domain layer.

### Repository
Repository has the implementation of the repository contract defined in the domain layer. They interact with data sources to retrieve data.
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

# Common
Common has cores and extensions.
For example.
Constants,
Resp(Resp is used as an data observer to the business logic, will have data reponse and state(Loading, Data, Error)),
Output(Output is used as an UI observer to transform the UI according to ViewModel respond. It will handle most duplicated UI properties such as showing loading and error messages).

# Dependency Injection
Dependency injection is used to provide dependencies to different layer of the app.
It help in managing the dependencies and allows easy swapping of implementations. 
For example. Dagger-Hilt can be used for dependency injection.

# Screenshots
## Dark Mode
Home Screen               |  Detail Screen               
:-------------------------:|:-------------------------:
<img src="https://github.com/shinewanna/BackgroundSmsPlugin/assets/39454969/92dbf485-230f-4123-aa9f-8d588d227da5" width="200" height="400"/>|<img src="https://github.com/shinewanna/BackgroundSmsPlugin/assets/39454969/471417c9-7958-472a-94ac-cab9801dcd6a" width="200" height="400"/>|

## Light Mode
Home Screen               |  Detail Screen               
:-------------------------:|:-------------------------:
<img src="https://github.com/shinewanna/BackgroundSmsPlugin/assets/39454969/d7df572e-be79-4910-8f9e-16ca875ac9e7" width="200" height="400"/>|<img src="https://github.com/shinewanna/BackgroundSmsPlugin/assets/39454969/eae87221-dd53-4415-9a77-d4e6006e61ab" width="200" height="400"/>|


