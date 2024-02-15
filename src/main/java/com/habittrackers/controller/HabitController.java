package com.habittrackers.controller;

import com.habittrackers.model.Habit;
import com.habittrackers.service.HabitService;
import com.habittrackers.service.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class HabitController {

    private HabitService habitService;

    @Autowired
    public HabitController(HabitService habitService, SqlService sqlService) {
        this.habitService = habitService;
    }

    @GetMapping("habit/get/all")
    public List<Habit> getAllHabits() {
        List<Habit> outHabit = habitService.getAllHabits();
        if (outHabit.isEmpty()){
            return null;
        }

        return outHabit;
    }

    @GetMapping("habit/get")
    public Habit getHabitById(@RequestParam String id) {
        Habit outHabit = habitService.getHabitById(id);
        if (outHabit.IsEmpty()){
            return null;
        }

        return outHabit;
    }

    @PostMapping("habit/add")
    public Boolean addNewHabit(@RequestParam String id,
                               @RequestParam String name,
                               @RequestParam String comments,
                               @RequestParam int start) {

        return habitService.addNewHabit(id, name, comments, start);
    }

    @DeleteMapping("habit/delete")
    public Boolean deleteHabit(@RequestParam String id) {
       return habitService.deleteHabit(id);
    }

}