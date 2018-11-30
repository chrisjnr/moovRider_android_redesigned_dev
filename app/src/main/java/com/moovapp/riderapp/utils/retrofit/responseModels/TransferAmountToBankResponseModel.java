package com.moovapp.riderapp.utils.retrofit.responseModels;

/**
 * Created by Lijo Mathew Theckanal on 14-Sep-18.
 */

public class TransferAmountToBankResponseModel {

    /**
     * status : true
     * test : RCP_3e7pv2v1k9uj35i
     * message : Amount mooved successfully
     */

    private boolean status;
    private String test;
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
