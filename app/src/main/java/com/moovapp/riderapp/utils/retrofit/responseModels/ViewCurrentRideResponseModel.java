package com.moovapp.riderapp.utils.retrofit.responseModels;

import java.util.List;

/**
 * Created by Lijo Mathew Theckanal on 26-Jul-18.
 */

public class ViewCurrentRideResponseModel {


    /**
     * status : true
     * message : Rides Lists
     * count : 1
     * data : [{"ride_id":338,"ride_trip_id":249,"ride_driver_id":5,"ride_booked_on":"2018-08-24 18:06:23","ride_booked_on_date":"2018-08-24","ride_booked_on_time":"18:06:23","ride_amount":"250","ride_status":"booked","ride_payment_status":"unpaid","ride_payment_time":"","ride_seats":2,"ride_from":"kazhakoottam","ride_to":"sreekaryam","driver_details":{"driver_id":5,"first_name":"Sics","last_name":"Tvm","email":"sics@gmail.com","institution_id":4,"institution_name":"university of Ilorin","phone":"9638527410","phone_country":"+91","gender":"male","vehicle_no":"Vvbbn","verified":0,"u_device_id":"8d85abea691d7860aeb4d8faaf4d354e96bb45c3c67d416b9170384418c2424a","car_model":"Hyundai Elantra","car_capacity":7,"license_no":"02/08/2018","license_expiry":"02/08/2018","dob":"02/08/2018","ratings":4,"wallet_balance":"0","image":"http://themoovapp.com/manage/uploads/userpic/a708bfd74ceb43d5.jpeg"}}]
     */

    private boolean status;
    private String message;
    private int count;
    private List<DataEntity> data;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * ride_id : 338
         * ride_trip_id : 249
         * ride_driver_id : 5
         * ride_booked_on : 2018-08-24 18:06:23
         * ride_booked_on_date : 2018-08-24
         * ride_booked_on_time : 18:06:23
         * ride_amount : 250
         * ride_status : booked
         * ride_payment_status : unpaid
         * ride_payment_time :
         * ride_seats : 2
         * ride_from : kazhakoottam
         * ride_to : sreekaryam
         * driver_details : {"driver_id":5,"first_name":"Sics","last_name":"Tvm","email":"sics@gmail.com","institution_id":4,"institution_name":"university of Ilorin","phone":"9638527410","phone_country":"+91","gender":"male","vehicle_no":"Vvbbn","verified":0,"u_device_id":"8d85abea691d7860aeb4d8faaf4d354e96bb45c3c67d416b9170384418c2424a","car_model":"Hyundai Elantra","car_capacity":7,"license_no":"02/08/2018","license_expiry":"02/08/2018","dob":"02/08/2018","ratings":4,"wallet_balance":"0","image":"http://themoovapp.com/manage/uploads/userpic/a708bfd74ceb43d5.jpeg"}
         */

        private int ride_id;
        private int ride_trip_id;
        private int ride_driver_id;
        private String ride_booked_on;
        private String ride_booked_on_date;
        private String ride_booked_on_time;
        private String ride_amount;
        private String ride_status;
        private String ride_payment_status;
        private String ride_payment_time;
        private int ride_seats;
        private String ride_from;
        private String ride_to;
        private DriverDetailsEntity driver_details;

        public int getRide_id() {
            return ride_id;
        }

        public void setRide_id(int ride_id) {
            this.ride_id = ride_id;
        }

        public int getRide_trip_id() {
            return ride_trip_id;
        }

        public void setRide_trip_id(int ride_trip_id) {
            this.ride_trip_id = ride_trip_id;
        }

        public int getRide_driver_id() {
            return ride_driver_id;
        }

        public void setRide_driver_id(int ride_driver_id) {
            this.ride_driver_id = ride_driver_id;
        }

        public String getRide_booked_on() {
            return ride_booked_on;
        }

        public void setRide_booked_on(String ride_booked_on) {
            this.ride_booked_on = ride_booked_on;
        }

        public String getRide_booked_on_date() {
            return ride_booked_on_date;
        }

        public void setRide_booked_on_date(String ride_booked_on_date) {
            this.ride_booked_on_date = ride_booked_on_date;
        }

        public String getRide_booked_on_time() {
            return ride_booked_on_time;
        }

        public void setRide_booked_on_time(String ride_booked_on_time) {
            this.ride_booked_on_time = ride_booked_on_time;
        }

        public String getRide_amount() {
            return ride_amount;
        }

        public void setRide_amount(String ride_amount) {
            this.ride_amount = ride_amount;
        }

        public String getRide_status() {
            return ride_status;
        }

        public void setRide_status(String ride_status) {
            this.ride_status = ride_status;
        }

        public String getRide_payment_status() {
            return ride_payment_status;
        }

        public void setRide_payment_status(String ride_payment_status) {
            this.ride_payment_status = ride_payment_status;
        }

        public String getRide_payment_time() {
            return ride_payment_time;
        }

        public void setRide_payment_time(String ride_payment_time) {
            this.ride_payment_time = ride_payment_time;
        }

        public int getRide_seats() {
            return ride_seats;
        }

        public void setRide_seats(int ride_seats) {
            this.ride_seats = ride_seats;
        }

        public String getRide_from() {
            return ride_from;
        }

        public void setRide_from(String ride_from) {
            this.ride_from = ride_from;
        }

        public String getRide_to() {
            return ride_to;
        }

        public void setRide_to(String ride_to) {
            this.ride_to = ride_to;
        }

        public DriverDetailsEntity getDriver_details() {
            return driver_details;
        }

        public void setDriver_details(DriverDetailsEntity driver_details) {
            this.driver_details = driver_details;
        }

        public static class DriverDetailsEntity {
            /**
             * driver_id : 5
             * first_name : Sics
             * last_name : Tvm
             * email : sics@gmail.com
             * institution_id : 4
             * institution_name : university of Ilorin
             * phone : 9638527410
             * phone_country : +91
             * gender : male
             * vehicle_no : Vvbbn
             * verified : 0
             * u_device_id : 8d85abea691d7860aeb4d8faaf4d354e96bb45c3c67d416b9170384418c2424a
             * car_model : Hyundai Elantra
             * car_capacity : 7
             * license_no : 02/08/2018
             * license_expiry : 02/08/2018
             * dob : 02/08/2018
             * ratings : 4
             * wallet_balance : 0
             * image : http://themoovapp.com/manage/uploads/userpic/a708bfd74ceb43d5.jpeg
             */

            private int driver_id;
            private String first_name;
            private String last_name;
            private String email;
            private int institution_id;
            private String institution_name;
            private String phone;
            private String phone_country;
            private String gender;
            private String vehicle_no;
            private int verified;
            private String u_device_id;
            private String car_model;
            private int car_capacity;
            private String license_no;
            private String license_expiry;
            private String dob;
            private int ratings;
            private String wallet_balance;
            private String image;

            public int getDriver_id() {
                return driver_id;
            }

            public void setDriver_id(int driver_id) {
                this.driver_id = driver_id;
            }

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

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getInstitution_id() {
                return institution_id;
            }

            public void setInstitution_id(int institution_id) {
                this.institution_id = institution_id;
            }

            public String getInstitution_name() {
                return institution_name;
            }

            public void setInstitution_name(String institution_name) {
                this.institution_name = institution_name;
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

            public int getVerified() {
                return verified;
            }

            public void setVerified(int verified) {
                this.verified = verified;
            }

            public String getU_device_id() {
                return u_device_id;
            }

            public void setU_device_id(String u_device_id) {
                this.u_device_id = u_device_id;
            }

            public String getCar_model() {
                return car_model;
            }

            public void setCar_model(String car_model) {
                this.car_model = car_model;
            }

            public int getCar_capacity() {
                return car_capacity;
            }

            public void setCar_capacity(int car_capacity) {
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

            public String getWallet_balance() {
                return wallet_balance;
            }

            public void setWallet_balance(String wallet_balance) {
                this.wallet_balance = wallet_balance;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }
}
