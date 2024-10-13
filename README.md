# Project Name

![License](https://img.shields.io/badge/license-Apache%202.0-brightgreen)

## Project Introduction

This is a GitHub user search application that allows users to search for GitHub users on the homepage and display a list of users. Users can click on a user in the list to enter their detailed information page, showcasing the user's basic information as well as the repositories they have created.

## Architecture Introduction

This project follows the **Clean Architecture** principles and adopts the **MVVM** (Model-View-ViewModel) architectural pattern to achieve better modularization and separation of concerns. By decoupling different business logic from UI logic, the project is easier to test and maintain.

### Multi-Modules Development

The project follows a **multi-modules** architecture for better maintainability, reusability, and separation of concerns. Below is an overview of the modules:

├── app               # Main Android application module
├── feature
│   ├── home          # Home feature module
│   └── details       # Details feature module
└── libs
    ├── network       # Network module (Retrofit integration)
    └── common        # Common utils library

### Modules Breakdown:

	•	app: The main Android app module. It serves as the entry point and orchestrates the various feature and library modules.
	•	feature/home: Handles the home screen of the app, which likely displays a list of repositories or user profiles retrieved from the GitHub API.
	•	feature/details: Displays detailed information for selected repositories or users from the home screen.
	•	libs/network: A library module that manages network requests using Retrofit to interact with the GitHub API.
	•	libs/common: Contains utility classes and helper methods that are shared across different modules (e.g., logging, string manipulations, view utilities).

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
