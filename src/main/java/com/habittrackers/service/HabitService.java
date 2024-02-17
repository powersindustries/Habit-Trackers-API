package com.habittrackers.service;

import com.habittrackers.model.Habit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HabitService {
    private SqlService sqlService;

    @Autowired
    public HabitService(SqlService sqlService) {
        this.sqlService = sqlService;
    }

    public List<Habit> getAllHabits() {
        List<Habit> outHabits = new ArrayList<Habit>();

        ResultSet resultSet = null;
        try {
            resultSet = sqlService.getStatement().executeQuery("SELECT * from habits");
            while (resultSet.next()) {
                Habit newHabit = new Habit();
                newHabit.id = resultSet.getInt("id");
                newHabit.name = resultSet.getString("name");
                newHabit.comments = resultSet.getString("comments");
                newHabit.start = resultSet.getString("start").hashCode();

                outHabits.add(newHabit);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return outHabits;
    }

    public Habit getHabitById(String id) {
        Habit outHabit = new Habit();
        ResultSet resultSet = null;
        try {
            String queryString = "SELECT * from habits WHERE id=" + id.hashCode();
            resultSet = sqlService.getStatement().executeQuery(queryString);
            while (resultSet.next()) {
                outHabit.id = resultSet.getInt("id");
                outHabit.name = resultSet.getString("name");
                outHabit.comments = resultSet.getString("comments");
                outHabit.start = resultSet.getString("start").hashCode();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return outHabit;
    }

    public Habit addNewHabit(String id, String name, String comments, int start) {

        Habit newHabit = new Habit();

        try {

            newHabit.id = id.hashCode();
            newHabit.name = name;
            newHabit.comments = comments;
            newHabit.start = start;

            String queryString = "INSERT INTO habits VALUES(";
            queryString += newHabit.id + ", \"";
            queryString += newHabit.name + "\", \"";
            queryString += newHabit.comments + "\", ";
            queryString += newHabit.start + ");";

            sqlService.getStatement().executeUpdate(queryString);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return newHabit;
    }

    public Boolean deleteHabit(String id) {
        try {
            String queryString = "DELETE FROM habits WHERE id = " + id.hashCode();
            sqlService.getStatement().executeUpdate(queryString);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return true;
    }

}