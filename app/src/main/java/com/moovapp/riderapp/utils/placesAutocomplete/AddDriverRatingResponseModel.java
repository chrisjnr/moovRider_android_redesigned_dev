package com.moovapp.riderapp.utils.placesAutocomplete;

/**
 * Created by Lijo Mathew Theckanal on 26-Jul-18.
 */

public class AddDriverRatingResponseModel {

    /**
     * status : true
     * message : Rating added successfully
     */

    private boolean status;
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
