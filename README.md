# Description - An MVP example
This project was an Android Challenge I was assigned to.
After completing it, I decided to share it as a sample project using MVP + RxJava and Dagger. Of course it needs improvement so it would be a great opportunity to learn and get new ideas about best practices, MVP, Dependency Injection and unit tests.
Feel free to share ideas, suggestions and PRs! =)

The challenge here is to build an app in which people can see the weather forecast. The user can select a city from a list of cities and see a 5 day weather forecast for the selected city.

**Required functionality:**

1. The weather information should be retrieved from a live 3rd party service;

2. The city list should default to Dublin, London, New York and Barcelona;

3. The user must be able to add to the city list;

4. The user must be able to remove a city from the city list;

5. All changes should be persistent. If the user adds or remove a city then the change should persists when the application is restarted;

6. The user must be able to refresh the weather forecast;

7. The application should present the forecasts as a graphical display.

# Implementation 

I used an architecture based on the MVP pattern and followed some Clean Architecture concepts.
The application is divided in three main packages: Model, Data and View.

- **Model:** Domain rules (not applicable in this case) and models.
- **Data:** Responsible for managing the flow of data used in the application. 
- **View (View + Presenter):** Responsible for the presentation layer.

### Libraries

1. **RxJava**
2. **Retrofit + OkHttp**
3. **ButterKnife**
4. **ORMLite**
5. **Gson**
6. **Dagger**

### API Key
After some feedbacks, I have removed the API Key exposure in this code. It is now located in my local ~/.gradle/gradle.properties file as "apiKey=INSERT_YOUR_API_KEY_HERE". You can see how I read this value in the app's build.gradle file.
If you want to test the app, you will have to put your own API Key in your gradle.properties file as mentioned above.
You can get your API Key here: https://developer.forecast.io/
