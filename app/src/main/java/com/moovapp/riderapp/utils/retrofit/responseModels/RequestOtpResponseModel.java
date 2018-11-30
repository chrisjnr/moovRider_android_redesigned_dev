package com.moovapp.riderapp.utils.retrofit.responseModels;

/**
 * Created by Lijo Mathew Theckanal on 23-Jul-18.
 */

public class RequestOtpResponseModel {

    /**
     * status : true
     * message : Otp send successfully
     * links : {"self":"http://themoovapp.com/api/v1/api/v1/update/phone/otp"}
     */

    private boolean status;
    private String message;
    private LinksEntity links;

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

    public LinksEntity getLinks() {
        return links;
    }

    public void setLinks(LinksEntity links) {
        this.links = links;
    }

    public static class LinksEntity {
        /**
         * self : http://themoovapp.com/api/v1/api/v1/update/phone/otp
         */

        private String self;

        public String getSelf() {
            return self;
        }

        public void setSelf(String self) {
            this.self = self;
        }
    }
}
