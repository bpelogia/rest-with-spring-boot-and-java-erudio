package br.com.erudio.framework.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date timeStamp;
    private String message;
    private String details;

    public ExceptionResponse(Date timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }


    /**
     * get field
     *
     * @return timeStamp
     */
    public Date getTimeStamp() {
        return this.timeStamp;
    }

    /**
     * set field
     *
     * @param timeStamp
     */
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * get field
     *
     * @return message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * set field
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * get field
     *
     * @return details
     */
    public String getDetails() {
        return this.details;
    }

    /**
     * set field
     *
     * @param details
     */
    public void setDetails(String details) {
        this.details = details;
    }
}
