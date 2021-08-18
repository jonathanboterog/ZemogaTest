Zemoga Mobile Test for Android
==============================

Application to apply for android developer position in Zemoga company.

![Alt Text](media/mobile_test.gif)

Application was developed using Android Studio 4.2.1 over windows OS, using
Androidx(that includes libraries for Android jetpack) and Kotlin language.

Application was designed under MVVM architecture that try to decouple the 
user interface as much as possible from the application logic.

for more information, see: https://developer.android.com/jetpack/guide

![](media/img1.png)

Dependencies
============

-   **viewpager2:** Support of segmented control to filter posts (All / Favorites)

-   **room:** Support of data persistence

-   **navigation:** Library for easy android navigation between activity/Fragments

-   **material:** for modern and high quality Look & feel

-   **lifecycle:** for easy managment of activity/fragments lifecycle states

-   **coroutines:** Concurrency design pattern to simplify code that executes 
asynchronously.

-   **dagger-hilt:** Dependency Injection

-   **lottie:** Beautiful animation support using lottie files(JSON)

-   **retrofit2:** Type-safe HTTP client, that simplify API/WebServices request to 
consume JSON or XML data,

-   **junit:** Unit testing.

-   **espresso:** Integration unit testing.


Mobile test architecture
========================

Layers of application architecture:
![](media/img2.png)

Each layer should speak only to their immediate friends. In this case, if we look at 
the scheme of the software architecture:
-   The UI can only communicate with the ViewModel
-   The ViewModel can only communicate with the UseCase
-   The UseCase can only communicate with the Repository
-   And the Repository can only communicate with the Datasource

Project application was separated in diferent packages for this purpose:

**di**: Package for dependency injection classes(Get Room Database instance).

**network.restapi**: Package to access remote datasource(Webservices/APIs)

**persistence** Package access database entities, dao and instance.

**repository** Package to handle data operations. They provide a clean API so that 
the rest of the app can retrieve this data easily

**ui-viewmodel** Package that provides the data for a specific UI component, 
such as a fragment or activity, and contains data-handling business logic to 
communicate with the model.

**ui** Package for activity/fragments and all related to user interface interaction.

Application functionalities
============================

