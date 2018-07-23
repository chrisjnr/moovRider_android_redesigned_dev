package com.moovapp.rider.utils.retrofit.responseModels;

/**
 * Created by Lijo Mathew Theckanal on 21-Jul-18.
 */

public class BookRideResponseModel {

    /**
     * status : true
     * data : {"driver_details":{"first_name":null,"last_name":null,"phone":null,"phone_country":null,"gender":null,"vehicle_no":null,"car_model":"abc","car_capacity":"abc","license_no":"abc","license_expiry":"abc","dob":"abc","ratings":0,"image":null},"distance_to_ride":1.47,"drive_details":{"distance":"20,8 km","time":"41 min"}}
     * message : Ride booked
     * links : {"self":"http://localhost/moov/api/v1/moov/api/v1/ride/book_now"}
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
         * driver_details : {"first_name":null,"last_name":null,"phone":null,"phone_country":null,"gender":null,"vehicle_no":null,"car_model":"abc","car_capacity":"abc","license_no":"abc","license_expiry":"abc","dob":"abc","ratings":0,"image":null}
         * distance_to_ride : 1.47
         * drive_details : {"distance":"20,8 km","time":"41 min"}
         */

        private DriverDetailsEntity driver_details;
        private double distance_to_ride;
        private DriveDetailsEntity drive_details;

        public DriverDetailsEntity getDriver_details() {
            return driver_details;
        }

        public void setDriver_details(DriverDetailsEntity driver_details) {
            this.driver_details = driver_details;
        }

        public double getDistance_to_ride() {
            return distance_to_ride;
        }

        public void setDistance_to_ride(double distance_to_ride) {
            this.distance_to_ride = distance_to_ride;
        }

        public DriveDetailsEntity getDrive_details() {
            return drive_details;
        }

        public void setDrive_details(DriveDetailsEntity drive_details) {
            this.drive_details = drive_details;
        }

        public static class DriverDetailsEntity {
            /**
             * first_name : null
             * last_name : null
             * phone : null
             * phone_country : null
             * gender : null
             * vehicle_no : null
             * car_model : abc
             * car_capacity : abc
             * license_no : abc
             * license_expiry : abc
             * dob : abc
             * ratings : 0
             * image : null
             */

            private String first_name;
            private String last_name;
            private String phone;
            private String phone_country;
            private String gender;
            private String vehicle_no;
            private String car_model;
            private String car_capacity;
            private String license_no;
            private String license_expiry;
            private String dob;
            private int ratings;
            private String image;

            public String getFirst_name() {
                return first_name;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;
            }

            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPhone_country() {
                return phone_country;
            }

            public void setPhone_country(String phone_country) {
                this.phone_country = phone_country;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getVehicle_no() {
                return vehicle_no;
            }

            public void setVehicle_no(String vehicle_no) {
                this.vehicle_no = vehicle_no;
            }

            public String getCar_model() {
                return car_model;
            }

            public void setCar_model(String car_model) {
                this.car_model = car_model;
            }

            public String getCar_capacity() {
                return car_capacity;
            }

            public void setCar_capacity(String car_capacity) {
                this.car_capacity = car_capacity;
            }

            public String getLicense_no() {
                return license_no;
            }

            public void setLicense_no(String license_no) {
                this.license_no = license_no;
            }

            public String getLicense_expiry() {
                return license_expiry;
            }

            public void setLicense_expiry(String license_expiry) {
                this.license_expiry = license_expiry;
            }

            public String getDob() {
                return dob;
            }

            public void setDob(String dob) {
                this.dob = dob;
            }

            public int getRatings() {
                return ratings;
            }

            public void setRatings(int ratings) {
                this.ratings = ratings;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }

        public static class DriveDetailsEntity {
            /**
             * distance : 20,8 km
             * time : 41 min
             */

            private String distance;
            private String time;

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }

    public static class LinksEntity {
        /**
         * self : http://localhost/moov/api/v1/moov/api/v1/ride/book_now
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
