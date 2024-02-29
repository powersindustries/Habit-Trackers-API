package com.habittrackers.service;

import com.habittrackers.model.Habit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

@Service
public class HabitService {
    private SqlService sqlService;


    // --------------------------------------------------------------
    // --------------------------------------------------------------
    @Autowired
    public HabitService(SqlService sqlService) {
        this.sqlService = sqlService;
    }


    // --------------------------------------------------------------
    // --------------------------------------------------------------
    public Habit getHabitById(String id) {
        Habit outHabit = new Habit();
        String queryString = String.format("SELECT * from habits WHERE id = %s", id.hashCode());

        try {
            ResultSet resultSet = null;
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


    // --------------------------------------------------------------
    // --------------------------------------------------------------
    public Habit addNewHabit(String id, String name, String comments, int start) {
        Habit newHabit = new Habit();
        newHabit.id = id.hashCode();
        newHabit.name = name;
        newHabit.comments = comments;
        newHabit.start = start;

        String queryString = String.format(
                "INSERT INTO habits VALUES( %s, '%s', '%s', %s);",
                newHabit.id,
                newHabit.name,
                newHabit.comments,
                newHabit.start);

        try {
            sqlService.getStatement().executeUpdate(queryString);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return newHabit;
    }


    // --------------------------------------------------------------
    // --------------------------------------------------------------
    public Boolean deleteHabit(String id) {
        try {
            String queryString = String.format("DELETE FROM habits WHERE id = %s", id.hashCode());
            sqlService.getStatement().executeUpdate(queryString);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return true;
    }


    // --------------------------------------------------------------
    // --------------------------------------------------------------
    public Boolean resetHabitStartById(String id) {
        String queryString = String.format("UPDATE habits SET start = %s WHERE id = %s ;", Instant.now().getEpochSecond(), id.hashCode());
        try {
            sqlService.getStatement().executeUpdate(queryString);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return true;
    }
}
