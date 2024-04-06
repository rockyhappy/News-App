# New App

This application provides users with the latest news articles fetched from a remote server. It's built using Kotlin and XML with the MVVM architecture, incorporating dependency injection using Dagger Hilt and local storage using Room Database. Users can search for news articles, paginate through the results, and save them for offline viewing. Additionally, the app checks for internet connectivity to ensure seamless operation. News articles are displayed using WebView for a rich browsing experience.

## Features

- Browse the latest news articles fetched from a remote server.
- Search for specific news articles based on keywords.
- Paginate through the search results to view more articles.
- Save news articles locally for offline viewing.
- Automatic internet connectivity check to ensure seamless operation.
- View news articles using WebView for a rich browsing experience.

## Screenshots

Here are some screenshots showcasing the functionality of the application:

<img src="https://github.com/rockyhappy/News-App/blob/master/example/WhatsApp%20Image%202024-04-07%20at%202.54.17%20AM.jpeg" alt="News App Screenshot 1" width="250">
<img src="https://github.com/rockyhappy/News-App/blob/master/example/WhatsApp%20Image%202024-04-07%20at%202.54.18%20AM.jpeg" alt="News App Screenshot 2" width="250">
<img src="https://github.com/rockyhappy/News-App/blob/master/example/WhatsApp%20Image%202024-04-07%20at%202.54.20%20AM.jpeg" alt="News App Screenshot 3" width="250">

https://firebasestorage.googleapis.com/v0/b/twingle-c1acb.appspot.com/o/WhatsApp%20Video%202024-04-07%20at%202.54.20%20AM.mp4?alt=media&token=bd667ef2-2ab3-49c4-bf77-e17ba2b3b5e8


## Installation

1. Clone this repository to your local machine:

```
git clone https://github.com/your_username/news_app.git
```

2. Open the project in Android Studio.

3. Build and run the application on an Android device or emulator.

## Usage

- Launch the application on your device.
- Browse through the latest news articles or use the search functionality to find specific articles.
- Paginate through the search results by clicking on the next or previous buttons.
- Tap on an article to view its details in a WebView.
- Save articles for offline viewing by clicking on the save button.
- Ensure that your device has an active internet connection for fetching the latest news.

## Dependencies

- [Dagger Hilt](https://dagger.dev/hilt/) - Dependency injection framework for Android.
- [Room Database](https://developer.android.com/training/data-storage/room) - Local database for storing news articles.
- [News API](https://newsapi.org/) - Remote server providing news articles.
- [ConnectivityManager](https://developer.android.com/reference/android/net/ConnectivityManager) - Used for internet connectivity check.
- WebView - Android system component used for displaying web content.

## Contributing

Contributions are welcome! If you encounter any issues or have suggestions for improvements, please feel free to open an issue or submit a pull request. Let's make this news app even better together!

