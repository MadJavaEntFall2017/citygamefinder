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

    /**
     * Gets the local hasError variable
     *
     * @return the local hasError variable
     */
    public boolean getHasError() {
        return hasError;
    }

    /**
     * Sets the local hasError variable
     *
     * @param hasError the value to set the local hasError variable
     */
    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    /**
     * Gets the local message variable
     *
     * @return the local message variable
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the local message variable
     *
     * @param message the value to set the local message variable
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the local url variable
     *
     * @return the local url variable
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the local url variable
     *
     * @param url the value to set the local url variable
     */
    public void setUrl(String url) {
        this.url = url;
    }
}

