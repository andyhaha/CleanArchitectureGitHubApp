# Project Name

![License](https://img.shields.io/badge/license-Apache%202.0-brightgreen)

## Project Introduction

This is a GitHub user search application that allows users to search for GitHub users on the homepage and display a list of users. Users can click on a user in the list to enter their detailed information page, showcasing the user's basic information as well as the repositories they have created.

## Architecture Introduction

This project follows the **Clean Architecture** principles and adopts the **MVVM** (Model-View-ViewModel) architectural pattern to achieve better modularization and separation of concerns. By decoupling different business logic from UI logic, the project is easier to test and maintain.

### Multi-Modules Development

The project is developed using **Android's multi-modules** approach, which allows for better separation of concerns and promotes reusability. Each module can focus on a specific feature or layer, making the codebase more organized and manageable.

## Technologies Used

This project utilizes the following technology stack:

- **Jetpack Compose**: For building the user interface, simplifying the declarative programming approach for UI.
- **MVVM**: Implements the MVVM architectural pattern to separate view logic from business logic.
- **Clean Architecture**: Adopts a clean architecture design to improve code maintainability and testability.
- **Retrofit**: Used for network requests, simplifying the process of API calls.
- **Room Database**: Provides local database support for easy data storage and querying.
- **Moshi**: Used for parsing JSON data, enhancing the efficiency of data serialization and deserialization.
- **Coil**: For image loading, offering smooth image processing and caching functionality.
- **Flows**: Utilizes Kotlin Coroutines' Flows for asynchronous programming and reactive programming.
- **GitHub Authorize**: Uses GitHub OAuth for authorization, ensuring users can safely access and search GitHub data.

## How to Run the Project

1. Clone this repository:
   ```bash
   git clone https://github.com/andyhaha/GitHubApp.git

2.	Import the project into Android Studio.
3.	Ensure you are using Kotlin version 1.5 or above and configure Gradle.
4.	Run the application.

Contributing

We welcome suggestions and contributions to this project! Please create a Pull Request or submit an issue.

License

This project is licensed under the Apache License 2.0.
