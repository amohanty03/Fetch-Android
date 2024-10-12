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
- **Build System**: Gradle
- **Version Control**: Git

---

## Setup & Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/amohanty03/Fetch-Android
   cd fetch-android
