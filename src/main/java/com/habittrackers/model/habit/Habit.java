package com.habittrackers.model.habit;

public class Habit {
    public int id;
    public String name;
    public String comments;
    public int start;


    public Habit() {
        id = 0;
        name = "";
        comments = "";
        start = 0;
    }

    public Habit(int id, String name, String comments, int start) {
        this.id = id;
        this.name = name;
        this.comments = comments;
        this.start = start;
    }


    public Boolean IsEmpty() {
        return id == 0 && name.isEmpty() && comments.isEmpty() && start <= 0;
    }


}