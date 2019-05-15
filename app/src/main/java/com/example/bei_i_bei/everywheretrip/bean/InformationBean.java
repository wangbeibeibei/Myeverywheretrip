package com.example.bei_i_bei.everywheretrip.bean;

public class InformationBean {


    /**
     * code : 0
     * desc :
     * result : {"uid":492748,"description":"","balance":"0.00","userName":"啦啦啦","photo":"http://cdn.banmi.com/banmiapp/user/logo/CF64B754-DF47-4BEF-961B-07AEFCAF3D89.jpg","gender":"M","email":"","phone":""}
     */

    private int code;
    private String desc;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * uid : 492748
         * description :
         * balance : 0.00
         * userName : 啦啦啦
         * photo : http://cdn.banmi.com/banmiapp/user/logo/CF64B754-DF47-4BEF-961B-07AEFCAF3D89.jpg
         * gender : M
         * email :
         * phone :
         */

        private int uid;
        private String description;
        private String balance;
        private String userName;
        private String photo;
        private String gender;
        private String email;
        private String phone;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "uid=" + uid +
                    ", description='" + description + '\'' +
                    ", balance='" + balance + '\'' +
                    ", userName='" + userName + '\'' +
                    ", photo='" + photo + '\'' +
                    ", gender='" + gender + '\'' +
                    ", email='" + email + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

}
