package com.habittrackers.controller;

import com.habittrackers.model.ErrorResponse;
import com.habittrackers.model.SuccessResponse;
import com.habittrackers.model.habit.Habit;
import com.habittrackers.service.HabitService;
import com.habittrackers.service.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "v1/habits")
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
            ErrorResponse.ErrorDetail errorDetail = new ErrorResponse.ErrorDetail();
            errorDetail.setCode(404);
            errorDetail.setMessage(String.format("Failed to find habit with id (%s)", id));

            ErrorResponse error = new ErrorResponse();
            error.setStatus("error");
            error.setError(errorDetail);

            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        SuccessResponse<Habit> successResponse = new SuccessResponse();
        successResponse.setSuccessStatus();
        successResponse.setData(outHabit);
        successResponse.setMessage("Habit Object was returned successfully.");

        return new ResponseEntity<>(successResponse, HttpStatus.OK);
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

            ErrorResponse.ErrorDetail errorDetail = new ErrorResponse.ErrorDetail();
            errorDetail.setCode(404);
            errorDetail.setMessage(String.format(
                    "Failed to create the new habit with id: %s, name: %s, comments: %s, start %s",
                    id,
                    name,
                    comments,
                    start));

            ErrorResponse error = new ErrorResponse();
            error.setStatus("error");
            error.setError(errorDetail);

            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } else {

            SuccessResponse<Habit> successResponse = new SuccessResponse();
            successResponse.setSuccessStatus();
            successResponse.setData(newHabit);
            successResponse.setMessage("Sucessfully added a new habit with the data found in the data object.");

            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }
    }


    // --------------------------------------------------------------
    // Removes a habit entry from the SQL database based on habit id.
    // --------------------------------------------------------------
    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteHabit(@RequestParam String id) {
        if (habitService.deleteHabit(id)) {
            SuccessResponse<Object> successResponse = new SuccessResponse();
            successResponse.setSuccessStatus();
            successResponse.setData("");
            successResponse.setMessage(String.format(
                    "Sucessfully deleted habit with id: %s",
                    id));

            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        } else {
            ErrorResponse.ErrorDetail errorDetail = new ErrorResponse.ErrorDetail();
            errorDetail.setCode(404);
            errorDetail.setMessage(String.format(
                    "Failed to delete habit with the id:  %s",
                    id));

            ErrorResponse error = new ErrorResponse();
            error.setStatus("error");
            error.setError(errorDetail);

            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }


    // --------------------------------------------------------------
    // Resets the habit's start time to the current unix time.
    // --------------------------------------------------------------
    @PutMapping("/reset")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> putResetHabitById(@RequestParam String id) {
        if (habitService.resetHabitStartById(id)) {
            SuccessResponse<Object> successResponse = new SuccessResponse();
            successResponse.setSuccessStatus();
            successResponse.setData("");
            successResponse.setMessage(String.format(
                    "Sucessfully reset habit with id: %s",
                    id));

            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        } else {
            ErrorResponse.ErrorDetail errorDetail = new ErrorResponse.ErrorDetail();
            errorDetail.setCode(404);
            errorDetail.setMessage(String.format(
                    "Failed to reset habit with the id:  %s",
                    id));

            ErrorResponse error = new ErrorResponse();
            error.setStatus("error");
            error.setError(errorDetail);

            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }


    // --------------------------------------------------------------
    // Edit the habit's name, comments, and start time by ID.
    // --------------------------------------------------------------
    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> putEditHabitById(
            @RequestParam String id,
            @RequestParam String name,
            @RequestParam String comments,
            @RequestParam int start) {
        if (habitService.editHabitStartById(id, name, comments, start)) {
            SuccessResponse<Object> successResponse = new SuccessResponse();
            successResponse.setSuccessStatus();
            successResponse.setData("");
            successResponse.setMessage(String.format(
                    "Sucessfully edited habit with id: %s",
                    id));

            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        } else {
            ErrorResponse.ErrorDetail errorDetail = new ErrorResponse.ErrorDetail();
            errorDetail.setCode(404);
            errorDetail.setMessage(String.format(
                    "Failed to edit habit with the id:  %s",
                    id));

            ErrorResponse error = new ErrorResponse();
            error.setStatus("error");
            error.setError(errorDetail);

            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }
}
