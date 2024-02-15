package com.habittrackers.service;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class SqlService {

    private String connectionURL = "CONNECTION URL";
    private String userId = "USER NAME";
    private String password = "PASSWORD";
    private Connection connection;
    private Statement statement;

    public SqlService() {
        try {
            connection = DriverManager.getConnection(connectionURL, userId, password);
            statement = connection.createStatement();
        } catch (SQLException ex) {
            System.out.println("ERROR: SQL connection failed with error: " + ex.getMessage());
        }
    }

    public Statement getStatement() { return statement; }

}
