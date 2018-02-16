# Requirements Document
## 1 Introduction
SunlightSpot is a simple weather application for Android. Is not a large application. This application aimed to help user to get the lastest forecast information for user's location. This application shows realy important information about the weather. For instance, current temperature, hudimity and so on. The main feature of the application is a possibility to select a favorite animal. This animal will be appear anytime when user runs the application.

## 2 User Requirements
### 2.1 Software Interfaces
The application must be written on Java. It's an Android application. It also uses [Open Weather API](http://openweathermap.org/api) forecast data in JSON format.
### 2.2 User Interfaces
User interface mookups are placed inside [/mockups](/docs/mockups) folder

Today Forecast Activity:

![Today Forecast Activity](/docs/mockups/Today%20Forecast%20Activity.png)

Tomorrow Forecast Activity:

![Tomorrow Forecast Activity](/docs/mockups/Tomorrow%20Forecast%20Activity.png)

This Week Forecast Activity:

![This Week Forecast Activity](/docs/mockups/This%20Week%20Forecast%20Activity.png)
### 2.3 User Characteristics
A typical user is a person who want to get forecast information quickly. User doesn't want to looking for the forecast in the Internet for a long time. User want to get the information in few steps using the smartphone. Also user likes animals.

### 2.4 Assumptions and Dependencies
#### Internet connection
This weather application requires a stable Internet connection for using.
#### Android smartphone
To use it, user should use a modern Android smartphone.

## 3 System Requirements
### 3.1 Functional Requirements
#### User can:
- View current weather information
- View detailed information about today weather in different hours
- View detailid information about tomorrow weather forecast
- View brief information about this week weather forecast
- Change location
- Select a preferred animal for displaying

### 3.2 Non-Functional Requirements
#### 3.2.1 SOFTWARE QUALITY ATTRIBUTES
##### Security
This application doesn't uses, manage, send and process any confidential user information. It may be possible for user to share one's location for quickly forecast access, but it's not mandatory.
##### Availability
User can use this application in any time from any place where Internet and an Android smartphone available.
##### Localization
The application supports only the English language.
#### 3.2.2 BUSINESS RULES
This application is absolutely free. User uses the application at one's own risk/responsibility.