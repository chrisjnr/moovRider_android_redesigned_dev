package com.moovapp.riderapp.utils.retrofit.responseModels;

/**
 * Created by Lijo Mathew Theckanal on 19-Jul-18.
 */

public class TalkToUsResponseModel {

    /**
     * status : true
     * message : Support sent successfully
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
