package com.purrfectpawtners.purrfectpawtners.exception;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details){
        // explicitly calling the default constructor of superclass("Object")
        // ensures that any initialisation/setup logic in default constructor of "Object" is executed before specific initialisation code
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp(){
        return timestamp;
    }

    public String getMessage(){
        return message;
    }

    public String getDetails(){
        return details;
    }
}
