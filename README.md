# Project Name

![License](https://img.shields.io/badge/license-Apache%202.0-brightgreen)

## Project Introduction

This is a GitHub user search application that allows users to search for GitHub users on the homepage and display a list of users. Users can click on a user in the list to enter their detailed information page, showcasing the user's basic information as well as the repositories they have created.

## Architecture Introduction

This project follows the **Clean Architecture** principles and adopts the **MVVM** (Model-View-ViewModel) architectural pattern to achieve better modularization and separation of concerns. By decoupling different business logic from UI logic, the project is easier to test and maintain.

Here’s an overview of your project structure, following the style of your example:

## Modules Overview

The project is divided into several modules:

- **:app** - Main Android app module that coordinates feature and library modules.
- **:feature:home** - Displays a list of GitHub users with the ability to search and store results locally using Room.
- **:feature:details** - Shows detailed information about selected users, including their profile and repositories.
- **:libs:network** - Manages network requests using Retrofit and Moshi for data serialization.
- **:libs:common** - Kotlin-only module providing utility functions and common classes used throughout the app.

## Technologies Used

This project utilizes the following technology stack:

- **Clean Architecture**: Implements clean architecture for better separation of concerns, enhancing code maintainability and testability..
- **MVVM**: Implements the MVVM architectural pattern to separate view logic from business logic.
- Flow & ViewModel: For managing UI-related data in a lifecycle-conscious way.
- **Jetpack Compose**: For building the user interface, simplifying the declarative programming approach for UI.
- **Dagger Hilt**: Dagger Hilt simplifies dependency injection across modules and enhances testability by enabling easy mocking for unit and UI tests.
- **Retrofit**: Used for network requests, simplifying the process of API calls.
- **Room Database**: Provides local database support for easy data storage and querying.
- **Moshi**: Used for parsing JSON data, enhancing the efficiency of data serialization and deserialization.
- **Coil**: For image loading, offering smooth image processing and caching functionality.
- **GitHub Authorize**: Uses GitHub OAuth for authorization, ensuring users can safely access and search GitHub data.

Contributing

We welcome suggestions and contributions to this project! Please create a Pull Request or submit an issue.

License

This project is licensed under the Apache License 2.0.
