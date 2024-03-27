# Habit Trackers API

Have you ever wanted to track the amount of time you've successfully kept a habit? Are you looking to start a new habit and want to timestamp when you started? The Habit Trackers API is for you!


### How it Works
The Habit Trackers API stores any number of Habit objects that contain a unique hashed ID, the name of your habit, comments or notes about the habit's goals (Optional), and the unix timestamp of when you started.

Habit data is stored in a MySQL database. The code assumes you're using MySQL, but feel free to change the connection code to whatever SQL database management system you prefer.

#### Habit Object
```Java
public class Habit {

    // Hashed unique ID used for searching.
    public int id;

    // Display name of the habit.
    public String name;

    // Comments or notes about the habit.
    public String comments;

    // Unix timestamp of when the habit was started. 
    // Can be used in the frontend for displaying the time that's elapsed since starting the habit.
    public int start;
}
```


### Current Endpoints
- Get existing Habit by ID.
- Add new Habit.
- Delete existing habit.
- Restart an existing habit's start time.
- Edit an existing habit's information (Name, Comments, Start Date).


### About
The Habit Trackers API was written in Java with the Spring Boot framework. This project is under the MIT license.

My motivation for creating this is API is my addiction to sugary snacks. I hope this can help others maintain or start new healthy habits. Cheers everyone!
