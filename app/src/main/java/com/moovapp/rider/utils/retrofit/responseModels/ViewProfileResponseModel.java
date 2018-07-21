package com.moovapp.rider.utils.retrofit.responseModels;

/**
 * Created by Lijo Mathew Theckanal on 21-Jul-18.
 */

public class ViewProfileResponseModel {


    /**
     * status : true
     * data : {"user_details":{"first_name":"test2","last_name":"user","email":"test19@test.com","institution_id":1,"institution_name":"sample school 1","phone":"888888888","phone_country":"","gender":"male","wallet_balance":"0","image":"male.png"},"user_pic_url":"http://themoovapp.com/manage/uploads/userpic/male.png","user_pic_url_100":"http://themoovapp.com/manage/uploads/userpic/croped/100/male.png","user_pic_url_200":"http://themoovapp.com/manage/uploads/userpic/croped/200/male.png"}
     * message : Driver detais
     * links : {"self":"http://themoovapp.com/api/v1/api/v1/view/details/user/4"}
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
         * user_details : {"first_name":"test2","last_name":"user","email":"test19@test.com","institution_id":1,"institution_name":"sample school 1","phone":"888888888","phone_country":"","gender":"male","wallet_balance":"0","image":"male.png"}
         * user_pic_url : http://themoovapp.com/manage/uploads/userpic/male.png
         * user_pic_url_100 : http://themoovapp.com/manage/uploads/userpic/croped/100/male.png
         * user_pic_url_200 : http://themoovapp.com/manage/uploads/userpic/croped/200/male.png
         */

        private UserDetailsEntity user_details;
        private String user_pic_url;
        private String user_pic_url_100;
        private String user_pic_url_200;

        public UserDetailsEntity getUser_details() {
            return user_details;
        }

        public void setUser_details(UserDetailsEntity user_details) {
            this.user_details = user_details;
        }

        public String getUser_pic_url() {
            return user_pic_url;
        }

        public void setUser_pic_url(String user_pic_url) {
            this.user_pic_url = user_pic_url;
        }

        public String getUser_pic_url_100() {
            return user_pic_url_100;
        }

        public void setUser_pic_url_100(String user_pic_url_100) {
            this.user_pic_url_100 = user_pic_url_100;
        }

        public String getUser_pic_url_200() {
            return user_pic_url_200;
        }

        public void setUser_pic_url_200(String user_pic_url_200) {
            this.user_pic_url_200 = user_pic_url_200;
        }

        public static class UserDetailsEntity {
            /**
             * first_name : test2
             * last_name : user
             * email : test19@test.com
             * institution_id : 1
             * institution_name : sample school 1
             * phone : 888888888
             * phone_country :
             * gender : male
             * wallet_balance : 0
             * image : male.png
             */

            private String first_name;
            private String last_name;
            private String email;
            private int institution_id;
            private String institution_name;
            private String phone;
            private String phone_country;
            private String gender;
            private String wallet_balance;
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

    public static class LinksEntity {
        /**
         * self : http://themoovapp.com/api/v1/api/v1/view/details/user/4
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
