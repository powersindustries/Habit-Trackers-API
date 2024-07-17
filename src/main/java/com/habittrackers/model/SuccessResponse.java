package com.habittrackers.model;

// ----------------------------------------
// Response example:
// ----------------------------------------

//{
//    "status":"success",
//    "data":{
//        "key1":"value1",
//        "key2":"value2",
//    },
//    "message":"Optional message here."
//}

public class SuccessResponse<T> {

    private String status;
    private T data;
    private String message;

    public String getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSuccessStatus() {
        this.status = "success";
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
