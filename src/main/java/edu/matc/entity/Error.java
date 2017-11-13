package edu.matc.entity;

/**
 * This class will hold all the error date for the citygamefinder call
 *
 * @author Great Lakes Team
 */
public class Error {
    private boolean hasError;
    private String message;
    private String url;

    public boolean getHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

