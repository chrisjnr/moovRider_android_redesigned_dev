package com.moovapp.rider.utils.retrofit.responseModels;

/**
 * Created by Lijo Mathew Theckanal on 21-Jul-18.
 */

public class BookRideResponseModel {


    /**
     * status : true
     * data : {"driver_details":{"driver_id":31,"first_name":"sics","last_name":"tvm","email":"remya@gmail.com","institution_id":0,"institution_name":null,"phone":"9446172550","phone_country":"+91","gender":"female","vehicle_no":"KL 123456","verified":0,"u_device_id":"di8g823msyU:APA91bFV6Ta0MS-T7iIHbOe0m5R-6yD4xNSJ0irRWnym5fPthofT3z9iI-FzgCEl9HRP0W2iwNjKJhEPiBPIE4IH48TMLsXBMrzEi1Lp9wdgHgeoqfKdzgJCOTIxFYXf_E0ScMI9wUbcju-9ZU7HWf7yI7DNmVLxow","car_model":"abc","car_capacity":"abc","license_no":"abc","license_expiry":"abc","dob":"abc","ratings":0,"wallet_balance":"0","image":"female.nopng"},"distance_to_drive_details":{"distance":"0,6 km","time":"2 min"},"trip_id":18,"ride_id":18}
     * message : Ride booked
     * links : {"self":"http://themoovapp.com/api/v1/api/v1/ride/book_now"}
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
         * driver_details : {"driver_id":31,"first_name":"sics","last_name":"tvm","email":"remya@gmail.com","institution_id":0,"institution_name":null,"phone":"9446172550","phone_country":"+91","gender":"female","vehicle_no":"KL 123456","verified":0,"u_device_id":"di8g823msyU:APA91bFV6Ta0MS-T7iIHbOe0m5R-6yD4xNSJ0irRWnym5fPthofT3z9iI-FzgCEl9HRP0W2iwNjKJhEPiBPIE4IH48TMLsXBMrzEi1Lp9wdgHgeoqfKdzgJCOTIxFYXf_E0ScMI9wUbcju-9ZU7HWf7yI7DNmVLxow","car_model":"abc","car_capacity":"abc","license_no":"abc","license_expiry":"abc","dob":"abc","ratings":0,"wallet_balance":"0","image":"female.nopng"}
         * distance_to_drive_details : {"distance":"0,6 km","time":"2 min"}
         * trip_id : 18
         * ride_id : 18
         */

        private DriverDetailsEntity driver_details;
        private DistanceToDriveDetailsEntity distance_to_drive_details;
        private int trip_id;
        private int ride_id;

        public DriverDetailsEntity getDriver_details() {
            return driver_details;
        }

        public void setDriver_details(DriverDetailsEntity driver_details) {
            this.driver_details = driver_details;
        }

        public DistanceToDriveDetailsEntity getDistance_to_drive_details() {
            return distance_to_drive_details;
        }

        public void setDistance_to_drive_details(DistanceToDriveDetailsEntity distance_to_drive_details) {
            this.distance_to_drive_details = distance_to_drive_details;
        }

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

        public static class DriverDetailsEntity {
            /**
             * driver_id : 31
             * first_name : sics
             * last_name : tvm
             * email : remya@gmail.com
             * institution_id : 0
             * institution_name : null
             * phone : 9446172550
             * phone_country : +91
             * gender : female
             * vehicle_no : KL 123456
             * verified : 0
             * u_device_id : di8g823msyU:APA91bFV6Ta0MS-T7iIHbOe0m5R-6yD4xNSJ0irRWnym5fPthofT3z9iI-FzgCEl9HRP0W2iwNjKJhEPiBPIE4IH48TMLsXBMrzEi1Lp9wdgHgeoqfKdzgJCOTIxFYXf_E0ScMI9wUbcju-9ZU7HWf7yI7DNmVLxow
             * car_model : abc
             * car_capacity : abc
             * license_no : abc
             * license_expiry : abc
             * dob : abc
             * ratings : 0
             * wallet_balance : 0
             * image : female.nopng
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
            private String car_capacity;
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

        public static class DistanceToDriveDetailsEntity {
            /**
             * distance : 0,6 km
             * time : 2 min
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
         * self : http://themoovapp.com/api/v1/api/v1/ride/book_now
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
