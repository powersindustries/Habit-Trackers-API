package com.habittrackers.controller;

import com.habittrackers.model.Habit;
import com.habittrackers.service.HabitService;
import com.habittrackers.service.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/habits")
public class HabitController {

    private HabitService habitService;


    // --------------------------------------------------------------
    // --------------------------------------------------------------
    @Autowired
    public HabitController(HabitService habitService, SqlService sqlService) {
        this.habitService = habitService;
    }


    // --------------------------------------------------------------
    // Returns a Habit objects from a habit id.
    // --------------------------------------------------------------
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getHabitById(@RequestParam String id) {
        Habit outHabit = habitService.getHabitById(id);
        if (outHabit.IsEmpty()) {
            return ResponseEntity
                    .status(404)
                    .body(String.format("Failed to find habit with id (%s)", id));
        }

        URI uriLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity
                .created(uriLocation)
                .body(outHabit);
    }


    // --------------------------------------------------------------
    // Adds a new habit entry to the SQL database.
    // --------------------------------------------------------------
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> addNewHabit(
            @RequestParam String id,
            @RequestParam String name,
            @RequestParam String comments,
            @RequestParam int start) {
        Habit newHabit = habitService.addNewHabit(id, name, comments, start);

        if (newHabit.IsEmpty()) {
            String responseBody = String.format(
                    "Failed to create the new habit with id: %s, name: %s, comments: %s, start %s",
                    id,
                    name,
                    comments,
                    start);

            return ResponseEntity
                    .badRequest()
                    .body(responseBody);
        } else {
            URI uriLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(id)
                    .toUri();

            return ResponseEntity
                    .created(uriLocation)
                    .body(newHabit);
        }
    }


    // --------------------------------------------------------------
    // Removes a habit entry from the SQL database based on habit id.
    // --------------------------------------------------------------
    @DeleteMapping("/")
    public ResponseEntity<Object> deleteHabit(@RequestParam String id) {
        if (habitService.deleteHabit(id)) {
            return ResponseEntity
                    .ok("Delete Succeeded.");
        } else {
            return ResponseEntity
                    .badRequest()
                    .body("Failed to delete habit with the id: " + id + ".");
        }
    }


    // --------------------------------------------------------------
    // Resets the habit's start time to the current unix time.
    // --------------------------------------------------------------
    @PutMapping("/")
    public ResponseEntity<Object> putResetHabitById(@RequestParam String id) {
        if (habitService.resetHabitStartById(id)) {
            return ResponseEntity
                    .ok("Reset Succeeded.");
        } else {
            return ResponseEntity
                    .badRequest()
                    .body("Failed to reset habit with the id: " + id + ".");
        }
    }


    // --------------------------------------------------------------
    // Edit the habit's name, comments, and start time by ID.
    // --------------------------------------------------------------
    @PutMapping("/")
    public ResponseEntity<Object> putEditHabitById(
            @RequestParam String id,
            @RequestParam String name,
            @RequestParam String comments,
            @RequestParam int start) {
        if (habitService.editHabitStartById(id, name, comments, start)) {
            return ResponseEntity
                    .ok("Reset Succeeded.");
        } else {
            return ResponseEntity
                    .badRequest()
                    .body("Failed to reset habit with the id: " + id + ".");
        }
    }
}
