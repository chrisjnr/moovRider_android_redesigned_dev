package com.moovapp.riderapp.utils.retrofit.responseModels;

import java.util.List;

/**
 * Created by Lijo Mathew Theckanal on 14-Sep-18.
 */

public class ListBanksResponseModel {

    /**
     * status : true
     * message : Banks retrieved
     * data : [{"name":"Access Bank","slug":"access-bank","code":"044","longcode":"044150149","gateway":"emandate","pay_with_bank":true,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":1,"createdAt":"2016-07-14T10:04:29.000Z","updatedAt":"2016-07-14T10:04:29.000Z"},{"name":"ALAT by WEMA","slug":"alat-by-wema","code":"035A","longcode":"035150103","gateway":"emandate","pay_with_bank":true,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":27,"createdAt":"2017-11-15T12:21:31.000Z","updatedAt":"2017-11-15T12:21:31.000Z"},{"name":"Citibank Nigeria","slug":"citibank-nigeria","code":"023","longcode":"023150005","gateway":"","pay_with_bank":false,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":2,"createdAt":"2016-07-14T10:04:29.000Z","updatedAt":"2016-07-14T10:04:29.000Z"},{"name":"Diamond Bank","slug":"diamond-bank","code":"063","longcode":"063150162","gateway":"emandate","pay_with_bank":true,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":3,"createdAt":"2016-07-14T10:04:29.000Z","updatedAt":"2016-07-14T10:04:29.000Z"},{"name":"Ecobank Nigeria","slug":"ecobank-nigeria","code":"050","longcode":"050150010","gateway":"","pay_with_bank":false,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":4,"createdAt":"2016-07-14T10:04:29.000Z","updatedAt":"2016-07-14T10:04:29.000Z"},{"name":"Enterprise Bank","slug":"enterprise-bank","code":"084","longcode":"084150015","gateway":"","pay_with_bank":false,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":5,"createdAt":"2016-07-14T10:04:29.000Z","updatedAt":"2016-07-14T10:04:29.000Z"},{"name":"Fidelity Bank","slug":"fidelity-bank","code":"070","longcode":"070150003","gateway":"emandate","pay_with_bank":true,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":6,"createdAt":"2016-07-14T10:04:29.000Z","updatedAt":"2016-07-14T10:04:29.000Z"},{"name":"First Bank of Nigeria","slug":"first-bank-of-nigeria","code":"011","longcode":"011151003","gateway":"emandate-disabled","pay_with_bank":false,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":7,"createdAt":"2016-07-14T10:04:29.000Z","updatedAt":"2016-07-14T10:04:29.000Z"},{"name":"First City Monument Bank","slug":"first-city-monument-bank","code":"214","longcode":"214150018","gateway":"emandate","pay_with_bank":true,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":8,"createdAt":"2016-07-14T10:04:29.000Z","updatedAt":"2016-07-14T10:04:29.000Z"},{"name":"Guaranty Trust Bank","slug":"guaranty-trust-bank","code":"058","longcode":"058152036","gateway":"ibank","pay_with_bank":true,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":9,"createdAt":"2016-07-14T10:04:29.000Z","updatedAt":"2016-07-14T10:04:29.000Z"},{"name":"Heritage Bank","slug":"heritage-bank","code":"030","longcode":"030159992","gateway":"etz","pay_with_bank":false,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":10,"createdAt":"2016-07-14T10:04:29.000Z","updatedAt":"2016-07-14T10:04:29.000Z"},{"name":"Jaiz Bank","slug":"jaiz-bank","code":"301","longcode":"301080020","gateway":null,"pay_with_bank":false,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":22,"createdAt":"2016-10-10T17:26:29.000Z","updatedAt":"2016-10-10T17:26:29.000Z"},{"name":"Keystone Bank","slug":"keystone-bank","code":"082","longcode":"082150017","gateway":"","pay_with_bank":false,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":11,"createdAt":"2016-07-14T10:04:29.000Z","updatedAt":"2016-07-14T10:04:29.000Z"},{"name":"MainStreet Bank","slug":"mainstreet-bank","code":"014","longcode":"014150331","gateway":"","pay_with_bank":false,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":12,"createdAt":"2016-07-14T10:04:29.000Z","updatedAt":"2016-07-14T10:04:29.000Z"},{"name":"Parallex Bank","slug":"parallex-bank","code":"526","longcode":"","gateway":null,"pay_with_bank":false,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":26,"createdAt":"2017-03-31T13:54:29.000Z","updatedAt":"2017-03-31T13:54:29.000Z"},{"name":"Providus Bank","slug":"providus-bank","code":"101","longcode":"","gateway":null,"pay_with_bank":false,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":25,"createdAt":"2017-03-27T16:09:29.000Z","updatedAt":"2017-03-27T16:09:29.000Z"},{"name":"Skye Bank","slug":"skye-bank","code":"076","longcode":"076151006","gateway":"etz","pay_with_bank":false,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":13,"createdAt":"2016-07-14T10:04:29.000Z","updatedAt":"2016-07-14T10:04:29.000Z"},{"name":"Stanbic IBTC Bank","slug":"stanbic-ibtc-bank","code":"221","longcode":"221159522","gateway":"etz","pay_with_bank":false,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":14,"createdAt":"2016-07-14T10:04:29.000Z","updatedAt":"2016-07-14T10:04:29.000Z"},{"name":"Standard Chartered Bank","slug":"standard-chartered-bank","code":"068","longcode":"068150015","gateway":"","pay_with_bank":false,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":15,"createdAt":"2016-07-14T10:04:29.000Z","updatedAt":"2016-07-14T10:04:29.000Z"},{"name":"Sterling Bank","slug":"sterling-bank","code":"232","longcode":"232150016","gateway":"emandate","pay_with_bank":true,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":16,"createdAt":"2016-07-14T10:04:29.000Z","updatedAt":"2016-07-14T10:04:29.000Z"},{"name":"Suntrust Bank","slug":"suntrust-bank","code":"100","longcode":"","gateway":null,"pay_with_bank":false,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":23,"createdAt":"2016-10-10T17:26:29.000Z","updatedAt":"2016-10-10T17:26:29.000Z"},{"name":"Union Bank of Nigeria","slug":"union-bank-of-nigeria","code":"032","longcode":"032080474","gateway":"etz","pay_with_bank":false,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":17,"createdAt":"2016-07-14T10:04:29.000Z","updatedAt":"2016-07-14T10:04:29.000Z"},{"name":"United Bank For Africa","slug":"united-bank-for-africa","code":"033","longcode":"033153513","gateway":"etz","pay_with_bank":false,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":18,"createdAt":"2016-07-14T10:04:29.000Z","updatedAt":"2016-07-14T10:04:29.000Z"},{"name":"Unity Bank","slug":"unity-bank","code":"215","longcode":"215154097","gateway":"emandate","pay_with_bank":true,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":19,"createdAt":"2016-07-14T10:04:29.000Z","updatedAt":"2016-07-14T10:04:29.000Z"},{"name":"Wema Bank","slug":"wema-bank","code":"035","longcode":"035150103","gateway":"","pay_with_bank":false,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":20,"createdAt":"2016-07-14T10:04:29.000Z","updatedAt":"2016-07-14T10:04:29.000Z"},{"name":"Zenith Bank","slug":"zenith-bank","code":"057","longcode":"057150013","gateway":"emandate","pay_with_bank":true,"active":true,"is_deleted":null,"country":"Nigeria","currency":"NGN","type":"nuban","id":21,"createdAt":"2016-07-14T10:04:29.000Z","updatedAt":"2016-07-14T10:04:29.000Z"}]
     */

    private boolean status;
    private String message;
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

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * name : Access Bank
         * slug : access-bank
         * code : 044
         * longcode : 044150149
         * gateway : emandate
         * pay_with_bank : true
         * active : true
         * is_deleted : null
         * country : Nigeria
         * currency : NGN
         * type : nuban
         * id : 1
         * createdAt : 2016-07-14T10:04:29.000Z
         * updatedAt : 2016-07-14T10:04:29.000Z
         */

        private String name;
        private String slug;
        private String code;
        private String longcode;
        private String gateway;
        private boolean pay_with_bank;
        private boolean active;
        private String is_deleted;
        private String country;
        private String currency;
        private String type;
        private int id;
        private String createdAt;
        private String updatedAt;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getLongcode() {
            return longcode;
        }

        public void setLongcode(String longcode) {
            this.longcode = longcode;
        }

        public String getGateway() {
            return gateway;
        }

        public void setGateway(String gateway) {
            this.gateway = gateway;
        }

        public boolean isPay_with_bank() {
            return pay_with_bank;
        }

        public void setPay_with_bank(boolean pay_with_bank) {
            this.pay_with_bank = pay_with_bank;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public String getIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(String is_deleted) {
            this.is_deleted = is_deleted;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
}
