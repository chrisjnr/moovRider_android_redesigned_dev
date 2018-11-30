package com.moovapp.riderapp.utils.retrofit.responseModels;

/**
 * Created by Lijo Mathew Theckanal on 21-Jul-18.
 */

public class UpdateProfilePicResponseModel {

    /**
     * status : true
     * message : Updated Successfully
     * data : {"user_pic_url":"http://themoovapp.com/manage/uploads/userpic/b0729646a6665584.jpg","user_pic_url_100":"http://themoovapp.com/manage/uploads/userpic/croped/100/b0729646a6665584.jpg","user_pic_url_200":"http://themoovapp.com/manage/uploads/userpic/croped/200/b0729646a6665584.jpg"}
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
         * user_pic_url : http://themoovapp.com/manage/uploads/userpic/b0729646a6665584.jpg
         * user_pic_url_100 : http://themoovapp.com/manage/uploads/userpic/croped/100/b0729646a6665584.jpg
         * user_pic_url_200 : http://themoovapp.com/manage/uploads/userpic/croped/200/b0729646a6665584.jpg
         */

        private String user_pic_url;
        private String user_pic_url_100;
        private String user_pic_url_200;

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
    }
}
