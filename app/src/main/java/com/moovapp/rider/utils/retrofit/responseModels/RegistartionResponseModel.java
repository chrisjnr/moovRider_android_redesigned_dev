package com.moovapp.rider.utils.retrofit.responseModels;

/**
 * Created by Lijo Mathew Theckanal on 19-Jul-18.
 */

public class RegistartionResponseModel {

    /**
     * status : true
     * data : {"user_details":{"user_id":43}}
     * message : Registration success
     * links : {"self":"http://themoovapp.com/api/v1/api/v1/auth/register","login":"http://themoovapp.com/api/v1/auth/login/email","forgot_password":"http://themoovapp.com/api/v1/auth/forgot"}
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
         * user_details : {"user_id":43}
         */

        private UserDetailsEntity user_details;

        public UserDetailsEntity getUser_details() {
            return user_details;
        }

        public void setUser_details(UserDetailsEntity user_details) {
            this.user_details = user_details;
        }

        public static class UserDetailsEntity {
            /**
             * user_id : 43
             */

            private int user_id;

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }
        }
    }

    public static class LinksEntity {
        /**
         * self : http://themoovapp.com/api/v1/api/v1/auth/register
         * login : http://themoovapp.com/api/v1/auth/login/email
         * forgot_password : http://themoovapp.com/api/v1/auth/forgot
         */

        private String self;
        private String login;
        private String forgot_password;

        public String getSelf() {
            return self;
        }

        public void setSelf(String self) {
            this.self = self;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getForgot_password() {
            return forgot_password;
        }

        public void setForgot_password(String forgot_password) {
            this.forgot_password = forgot_password;
        }
    }
}
