# CleanArchitectureGitHubApp

![License](https://img.shields.io/badge/license-Apache%202.0-brightgreen)

[简体中文版说明 >>>](https://github.com/andyhaha/CleanArchitectureGitHubApp/blob/main/README_cn.md)

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
- **Jetpack Compose**: For building the user interface, simplifying the declarative programming approach for UI.
- **MVVM**: Implements the MVVM architectural pattern to separate view logic from business logic.
- **Flow**: Used for handling asynchronous data streams, providing a reactive approach to manage and emit data updates in a lifecycle-aware manner.
- **Dagger Hilt**: Dagger Hilt simplifies dependency injection across modules and enhances testability by enabling easy mocking for unit and UI tests.
- **Retrofit**: Used for network requests, simplifying the process of API calls.
- **Moshi**: Used for JSON parsing, simplifying the conversion between JSON and Kotlin/Java objects.
- **Paging3**: Used to implement efficient pagination for user search, enabling smooth loading of data in chunks as the user scrolls.
- **Room Database**: Provides local database support for easy data storage and querying.
- **Moshi**: Used for parsing JSON data, enhancing the efficiency of data serialization and deserialization.
- **Coil**: For image loading, offering smooth image processing and caching functionality.
- **GitHub Authorize**: Uses GitHub OAuth for authorization, ensuring users can safely access and search GitHub data.
- **Room Database Testing**: Ensures reliable data storage by testing CRUD operations and data integrity in the local SQLite database through unit and instrumented tests.
Contributing

We welcome suggestions and contributions to this project! Please create a Pull Request or submit an issue.

## License

Copyright (c) [2024] [Andy]

Permission is hereby granted, free of charge, to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of this software, and to permit others to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES, OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT, OR OTHERWISE, ARISING FROM, OUT OF, OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
