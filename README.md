# Jet Weather Forecast

**Jet Weather Forecast** is an intuitive and responsive weather application built using the latest Android technologies. This app provides users with real-time weather forecasts for their favorite cities, ensuring they stay informed about current and future weather conditions.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Architecture](#architecture)
- [Setup Instructions](#setup-instructions)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Real-Time Weather Data**: Fetches accurate weather forecasts for up to 5 days using a reliable weather API.
- **Favorite Cities**: Users can save their favorite cities and quickly access their weather information.
- **Unit Settings**: Customize the temperature units (Celsius/Fahrenheit) to suit user preferences.
- **Navigation**: Smooth navigation between different app screens with a user-friendly interface.
- **Offline Support**: Leverages Room database to store favorite cities and units, enabling offline access to previously saved data.
- **Splash Screen**: An engaging splash screen that enhances user experience while loading the app.

## Technologies Used

- **Kotlin**: The primary programming language, allowing for concise and safe code.
- **Jetpack Compose**: Utilized for building modern and declarative UI components, enabling a responsive and intuitive user interface.
- **Room Database**: A powerful SQLite library for managing local data, facilitating easy storage and retrieval of user preferences and favorite cities.
- **Retrofit**: A type-safe HTTP client for Android that simplifies API calls and responses, enhancing network communication.
- **Coroutines**: Implemented for asynchronous programming, ensuring smooth and efficient operations, especially when dealing with network requests and database interactions.
- **Hilt**: A dependency injection library that simplifies the management of dependencies, promoting better code organization and testability.
- **Flow**: Used for handling asynchronous data streams, particularly when observing changes in favorite cities and unit settings.

## Architecture

The app follows a clean architecture pattern, separating concerns into distinct layers:

- **Data Layer**: Responsible for handling data sources, including network calls (using Retrofit) and local database interactions (using Room). This layer provides a repository pattern that abstracts the data operations.
- **Domain Layer**: Contains business logic, encapsulating use cases for fetching weather data, managing favorites, and adjusting unit settings.
- **Presentation Layer**: Built with Jetpack Compose for creating UI components that respond to state changes, ensuring a reactive and engaging user experience.

## Setup Instructions

To get started with the Jet Weather Forecast app, follow these steps:

1. **Clone the repository**:
    ```bash
    git clone https://github.com/muameh/JetWeatherApplication.git
    ```
2. **Open the project** in Android Studio.
3. **Sync Gradle** to download dependencies.
4. **Set up your API key**:
    - Obtain an API key from your weather data provider (e.g., OpenWeatherMap).
    - Replace `Constants.API_KEY` with your actual API key in the `Constants` file.
5. **Run the application** on an emulator or physical device.

## Contributing

Contributions are welcome! If you have suggestions for improvements or features, feel free to fork the repository and submit a pull request.

---

By showcasing the latest Android technologies and adhering to best practices, **Jet Weather Forecast** is not just a weather application but a demonstration of efficient architecture and modern development techniques. Join me on this journey to create a better weather forecasting experience!

![Alternatif Metin]("screenShots\Screenshot_1729440292.png")
