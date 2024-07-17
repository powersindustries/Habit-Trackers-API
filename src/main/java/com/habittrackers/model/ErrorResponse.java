package com.habittrackers.model;

// ----------------------------------------
// Error Response Object example.
// ----------------------------------------

//{
//    "status": "error"
//    "error": {
//        "code": 400, // Or whatever the error code is.
//        "message": "Message describing error."
//    }
//}

public class ErrorResponse {

    public static class ErrorDetail {
        private int code;
        private String message;

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


    private String status;
    private ErrorDetail error;

    public String getStatus() {
        return status;
    }

    public ErrorDetail getError() {
        return error;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setError(ErrorDetail error) {
        this.error = error;
    }

}