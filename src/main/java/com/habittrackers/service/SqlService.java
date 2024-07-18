package com.habittrackers.service;

import com.habittrackers.config.SqlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class SqlService {

    private SqlConfig sqlConfig;
    private Connection connection;
    private Statement statement;

    @Autowired
    public SqlService(SqlConfig sqlConfig) {
        this.sqlConfig = sqlConfig;

        try {
            connection = DriverManager.getConnection(
                    sqlConfig.getConnectionURL(),
                    sqlConfig.getUserId(),
                    sqlConfig.getPassword());

            statement = connection.createStatement();

        } catch (SQLException ex) {
            System.out.println("ERROR: SQL connection failed with error: " + ex.getMessage());
        }
    }

    public Statement getStatement() { return statement; }

}
