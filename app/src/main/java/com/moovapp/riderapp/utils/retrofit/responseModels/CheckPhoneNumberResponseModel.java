package com.moovapp.riderapp.utils.retrofit.responseModels;

/**
 * Created by Lijo Mathew Theckanal on 18-Sep-18.
 */

public class CheckPhoneNumberResponseModel {


    /**
     * status : true
     * data : {"u_id":11,"u_first_name":"Lijo","u_last_name":"Matew"}
     * message : Account exists
     */

    private boolean status;
    private DataEntity data;
    private String message;

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

    public static class DataEntity {
        /**
         * u_id : 11
         * u_first_name : Lijo
         * u_last_name : Matew
         */

        private int u_id;
        private String u_first_name;
        private String u_last_name;

        public int getU_id() {
            return u_id;
        }

        public void setU_id(int u_id) {
            this.u_id = u_id;
        }

        public String getU_first_name() {
            return u_first_name;
        }

        public void setU_first_name(String u_first_name) {
            this.u_first_name = u_first_name;
        }

        public String getU_last_name() {
            return u_last_name;
        }

        public void setU_last_name(String u_last_name) {
            this.u_last_name = u_last_name;
        }
    }
}
