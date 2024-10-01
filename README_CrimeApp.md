
# Crime Tracking Application - Android App

## Project Overview

This is an Android application developed to track and manage a list of "crimes" (fictional) using the MVVM (Model-View-ViewModel) architecture. The application allows users to view a list of crimes, add new crimes, and update the details of each crime. The crimes are stored in an SQLite database using **Room** for data persistence, and the app features **RecyclerView** for displaying a list of crimes, along with navigation features using **Jetpack Navigation**.

The app follows modern Android development best practices by utilizing Kotlin coroutines, LiveData, ViewModel, and Room for efficient data handling, and lifecycle management.

## Key Features

- **View Crime List**: Displays a list of crimes using **RecyclerView**.
- **Add New Crime**: Allows users to add new crimes with details such as title, date, and whether the crime is solved.
- **Edit Crime Details**: Users can edit the crime details such as title, date, and mark if the crime has been solved.
- **Date Picker Dialog**: Select and update the date for the crime entry using a date picker.
- **Persistence with Room**: Crime data is stored locally using SQLite with the help of the **Room** persistence library.
- **Navigation Component**: Navigates between the crime list and detail screens using Jetpack Navigation.

## File Descriptions

- **MainActivity.kt**: The entry point of the application. Sets the main content view.
- **CrimeListFragment.kt**: The fragment responsible for displaying a list of crimes using **RecyclerView**.
- **CrimeDetailFragment.kt**: The fragment that shows detailed information about a selected crime and allows the user to edit the crime's title, date, and status.
- **CrimeListAdapter.kt**: A custom adapter for the **RecyclerView** that binds the crime data to the views.
- **DatePickerFragment.kt**: A fragment that provides a date picker dialog for selecting a crime's date.
- **CrimeDetailViewModel.kt**: ViewModel for handling the business logic related to displaying and updating crime details.
- **CrimeListViewModel.kt**: ViewModel for handling the business logic related to retrieving the crime list.
- **CrimeRepository.kt**: Singleton repository for managing data operations between the Room database and the ViewModel.
- **Crime.kt**: Data class representing the "crime" entity in the Room database.

## Architecture

The project follows the **MVVM** architecture pattern with clear separation of concerns. The **ViewModel** handles the data and business logic, while the **View** (fragments) is responsible for rendering the UI. The **Repository** is a single source of truth for data management and persistence.

## Core Components

### RecyclerView and Adapter
- The crime list is displayed using **RecyclerView**, which provides an efficient way to display a large dataset.
- **CrimeListAdapter** binds the crime data to each list item and handles click events for navigating to the detail screen.

### Room Database
- **Crime** entity represents the database table.
- **CrimeRepository** handles CRUD operations on the Room database.
- **CrimeDatabase** provides the database instance using **Room**.

### Jetpack Navigation
- The app uses **Jetpack Navigation** to handle fragment transactions and navigate between the crime list and the detail screen.
- Navigation actions are defined in **nav_graph.xml**.

### Kotlin Coroutines
- Asynchronous operations, such as database queries and updates, are handled using **Kotlin coroutines**.
- The coroutines are scoped to the **ViewModel** for lifecycle-aware operation management.

## Installation and Setup

### Prerequisites

- Android Studio installed.
- Android SDK configured.

### Running the Project

1. Clone the repository to your local machine.
   ```bash
   git clone https://github.com/your-repo/crime-tracker-app.git
   ```

2. Open the project in Android Studio.

3. Build and run the app on an emulator or physical device.

## Usage

- **View Crime List**: The main screen shows a list of crimes. Click on any crime to view or edit its details.
- **Add New Crime**: Press the "New Crime" button to add a new crime entry.
- **Edit Crime**: Update the crime's title, date, and solved status on the detail screen.

## Future Enhancements

- **Search Feature**: Add functionality to search for crimes by title.
- **Filter Feature**: Add the ability to filter crimes based on whether they are solved or unsolved.
- **Image Attachments**: Allow users to attach images to crime entries.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

This project demonstrates proficiency in Android development using modern best practices, including **RecyclerView**, **Room**, **Kotlin Coroutines**, and **Jetpack Navigation**. The app structure follows the **MVVM architecture** pattern, ensuring a clean and maintainable codebase.

