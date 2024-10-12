# Fetch Android App

A native Android app built in **Java** that retrieves, filters, and displays data from a remote API. The app showcases data in a `RecyclerView` and provides functionality to refresh data. If any network errors occur, an error message and an icon are displayed.

---

## Features

- Fetches data from a remote API and displays it in a list.
- Filters out invalid entries (e.g., empty or `"null"` names).
- Groups items by `listId` and sorts them by `id` within each group.
- Displays a custom error icon and message when an issue occurs.
- Provides a **Refresh** button to re-fetch the data at any time.

---

## Tech Stack

- **Language**: Java
- **Layout**: XML
- **Networking**: Volley
- **Testing**: AndroidX, JUnit and Espresso
- **Build System**: Gradle
- **Version Control**: Git

---

## Setup & Installation

1. **Clone the repository:**

- Terminal
   ```
   git clone https://github.com/amohanty03/Fetch-Android
   cd Fetch-Android
   ```
   - Open Android Studio
   - Goto File > New > Import Project
   - Choose Fetch-Android
   - Click Open

- Android Studio (Recommended)
   - Open Android Studio
   - Go to File > New > Project from Version Control
   - Paste `https://github.com/amohanty03/Fetch-Android` into URL text field
   - Click Clone

2. **Setup AVD**

- Android Emulator
   - https://developer.android.com/studio/run/managing-avds 

3. **Build the project**

- Android Studio
   - Select Build > Make Project

4. **Run the project**

- **Run** > **Run app** or click the **Run icon** in the toolbar.

---

## Testing

- **Android Studio (Recommended)**
   - Due to the complexity of simulating lost network connection, I recommend utilizing Android Studio to run the tests individually.
   - Make sure the AVD is running and connected to the Internet. Right-click on the test file and click on ```Run <test-name>```. The program tests for:
      - ```MainActivityTest```
         - Tests the layout of the list page when connected to the Internet and existence of UI elements.
      - ```NetworkUnavailableTest```
         - Before running the test, disable the WiFi to simulate a lost connection - causing the program to show an error page.
      - ```NetworkAvailableTest```
         - Keep the WiFi disabled, this test simulates a user flow when it regains network connection. While connected, upon clicking the refresh button the user is redirected to the list of items.

---

## App Screens

