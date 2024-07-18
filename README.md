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
#### Get existing Habit by ID.
```json
{
  "status": "success",
  "data": {
    "id": "sample_id",
    "name": "sample name",
    "comments": "sample comment",
    "start": 12345
  },
  "message": "Habit Object was returned successfully."
}
```

#### Add new Habit.
```json
{
  "status": "success",
  "data": {
    "id": 1355705334,
    "name": "sample name",
    "comments": "sample comment",
    "start": 12345
  },
  "message": "Sucessfully added a new habit with the data found in the data object."
}
```

#### Delete existing habit.
```json
{
    "status": "success",
    "data": "",
    "message": "Sucessfully deleted habit with id: 123"
}
```

#### Restart an existing habit's start time.
```json
{
  "status": "success",
  "data": {
    "id": 1355705334,
    "name": "sample name",
    "comments": "sample comment",
    "start": 12345
  },
  "message": "Sucessfully reset habit with id: 123. Reset habit data is found in the data field of response."
}
```

#### Edit an existing habit's information (Name, Comments, Start Date).
```json
{
  "status": "success",
  "data": {
    "id": 1355705334,
    "name": "sample name",
    "comments": "sample comment",
    "start": 12345
  },
  "message": "Sucessfully edited habit with id: 123. Edited habit data is found in the data field of response."
}
```


### About
The Habit Trackers API was written in Java with the Spring Boot framework. This project is under the MIT license.

My motivation for creating this is API is my addiction to sugary snacks. I hope this can help others maintain or start new healthy habits. Cheers everyone!
