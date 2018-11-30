package com.moovapp.riderapp.utils.retrofit.responseModels;

import java.util.List;

/**
 * Created by Lijo Mathew Theckanal on 20-Jul-18.
 */

public class ViewCollegesResponseModel {


    /**
     * status : true
     * data : {"details":[{"name":"Covenant University","id ":1},{"name":"Afe babalola university","id ":2},{"name":"Babcock University","id ":3},{"name":"University of Ilorin","id ":4}],"user_institute":3}
     * message : Amount calculated
     * links : {"self":"http://themoovapp.com/api/v2/api/v2/ride/view_colleges/42"}
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
         * details : [{"name":"Covenant University","id ":1},{"name":"Afe babalola university","id ":2},{"name":"Babcock University","id ":3},{"name":"University of Ilorin","id ":4}]
         * user_institute : 3
         */

        private int user_institute;
        private List<DetailsEntity> details;

        public int getUser_institute() {
            return user_institute;
        }

        public void setUser_institute(int user_institute) {
            this.user_institute = user_institute;
        }

        public List<DetailsEntity> getDetails() {
            return details;
        }

        public void setDetails(List<DetailsEntity> details) {
            this.details = details;
        }

        public static class DetailsEntity {
            /**
             * name : Covenant University
             * id  : 1
             */

            private String name;
            private int id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }

    public static class LinksEntity {
        /**
         * self : http://themoovapp.com/api/v2/api/v2/ride/view_colleges/42
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
