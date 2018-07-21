package com.moovapp.rider.utils.retrofit.responseModels;

/**
 * Created by Lijo Mathew Theckanal on 20-Jul-18.
 */

public class InitPaymentResponseModel {

    /**
     * status : true
     * message : Authorization URL created
     * data : {"authorization_url":"https://paystack.com/secure/k6bf9px5ag9r8xn","access_code":"k6bf9px5ag9r8xn","reference":"6573808902251e5927a8"}
     */

    private boolean status;
    private String message;
    private DataEntity data;

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

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * authorization_url : https://paystack.com/secure/k6bf9px5ag9r8xn
         * access_code : k6bf9px5ag9r8xn
         * reference : 6573808902251e5927a8
         */

        private String authorization_url;
        private String access_code;
        private String reference;

        public String getAuthorization_url() {
            return authorization_url;
        }

        public void setAuthorization_url(String authorization_url) {
            this.authorization_url = authorization_url;
        }

        public String getAccess_code() {
            return access_code;
        }

        public void setAccess_code(String access_code) {
            this.access_code = access_code;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }
    }
}
