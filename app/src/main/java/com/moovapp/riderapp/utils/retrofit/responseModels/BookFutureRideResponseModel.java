package com.moovapp.riderapp.utils.retrofit.responseModels;

/**
 * Created by Lijo Mathew Theckanal on 07-Sep-18.
 */

public class BookFutureRideResponseModel {

    /**
     * status : true
     * data : {"trip_id":276,"ride_id":379}
     * message : Ride booked
     * links : {"self":"http://themoovapp.com/api/v2/api/v2/ride/book_future"}
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
         * trip_id : 276
         * ride_id : 379
         */

        private int trip_id;
        private int ride_id;

        public int getTrip_id() {
            return trip_id;
        }

        public void setTrip_id(int trip_id) {
            this.trip_id = trip_id;
        }

        public int getRide_id() {
            return ride_id;
        }

        public void setRide_id(int ride_id) {
            this.ride_id = ride_id;
        }
    }

    public static class LinksEntity {
        /**
         * self : http://themoovapp.com/api/v2/api/v2/ride/book_future
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
