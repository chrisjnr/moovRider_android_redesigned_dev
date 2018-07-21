package com.moovapp.rider.utils.retrofit.responseModels;

/**
 * Created by Lijo Mathew Theckanal on 20-Jul-18.
 */

public class RideSearchResponseModel {

    /**
     * status : true
     * data : {"amount":293.2}
     * message : Amount calculated
     * links : {"self":"http://themoovapp.com/api/v1/api/v1/ride/search/amount/kottayam/trivandrum/2/yes"}
     */

    private boolean status;
    private DataEntity data;
    private String message;
    private LinksEntity links;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
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

    public static class DataEntity {
        /**
         * amount : 293.2
         */

        private double amount;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }

    public static class LinksEntity {
        /**
         * self : http://themoovapp.com/api/v1/api/v1/ride/search/amount/kottayam/trivandrum/2/yes
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
