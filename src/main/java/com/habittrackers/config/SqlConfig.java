package com.habittrackers.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SqlConfig {

    @Value("${sql.url}")
    private String connectionURL;

    @Value("${sql.username}")
    private String userId;

    @Value("${sql.password}")
    private String password;


    public String getConnectionURL() {
        return connectionURL;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}