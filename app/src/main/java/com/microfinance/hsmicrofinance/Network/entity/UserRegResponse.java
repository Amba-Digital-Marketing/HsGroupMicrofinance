package com.microfinance.hsmicrofinance.Network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserRegResponse {

    @SerializedName("user")
    private User__1 user;
    @SerializedName("token")
    @Expose
    private String token;

    public UserRegResponse() {
    }
    public UserRegResponse(User__1 user, String token) {
        super();
        this.user = user;
        this.token = token;
    }

    public User__1 getUser() {
        return user;
    }

    public void setUser(User__1 user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(UserRegResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("user");
        sb.append('=');
        sb.append(((this.user == null)?"<null>":this.user));
        sb.append(',');
        sb.append("token");
        sb.append('=');
        sb.append(((this.token == null)?"<null>":this.token));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }


    public class User__1 {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("kra")
        @Expose
        private String kra;
        @SerializedName("pin")
        @Expose
        private String pin;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("phone_verified_at")
        @Expose
        private String phoneVerifiedAt;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("account_number")
        @Expose
        private String accountNumber;
        @SerializedName("status")
        @Expose
        private int status;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("id")
        @Expose
        private int id;


        public User__1() {
        }

        public User__1(String name, String email, String kra, String pin, String image, String phoneVerifiedAt, String phone, String accountNumber, int status, String updatedAt, String createdAt, int id) {
            super();
            this.name = name;
            this.email = email;
            this.kra = kra;
            this.pin = pin;
            this.image = image;
            this.phoneVerifiedAt = phoneVerifiedAt;
            this.phone = phone;
            this.accountNumber = accountNumber;
            this.status = status;
            this.updatedAt = updatedAt;
            this.createdAt = createdAt;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getKra() {
            return kra;
        }

        public void setKra(String kra) {
            this.kra = kra;
        }

        public String getPin() {
            return pin;
        }

        public void setPin(String pin) {
            this.pin = pin;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPhoneVerifiedAt() {
            return phoneVerifiedAt;
        }

        public void setPhoneVerifiedAt(String phoneVerifiedAt) {
            this.phoneVerifiedAt = phoneVerifiedAt;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("email");
            sb.append('=');
            sb.append(((this.email == null)?"<null>":this.email));
            sb.append(',');
            sb.append("kra");
            sb.append('=');
            sb.append(((this.kra == null)?"<null>":this.kra));
            sb.append(',');
            sb.append("pin");
            sb.append('=');
            sb.append(((this.pin == null)?"<null>":this.pin));
            sb.append(',');
            sb.append("image");
            sb.append('=');
            sb.append(((this.image == null)?"<null>":this.image));
            sb.append(',');
            sb.append("phoneVerifiedAt");
            sb.append('=');
            sb.append(((this.phoneVerifiedAt == null)?"<null>":this.phoneVerifiedAt));
            sb.append(',');
            sb.append("phone");
            sb.append('=');
            sb.append(((this.phone == null)?"<null>":this.phone));
            sb.append(',');
            sb.append("accountNumber");
            sb.append('=');
            sb.append(((this.accountNumber == null)?"<null>":this.accountNumber));
            sb.append(',');
            sb.append("status");
            sb.append('=');
            sb.append(this.status);
            sb.append(',');
            sb.append("updatedAt");
            sb.append('=');
            sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
            sb.append(',');
            sb.append("createdAt");
            sb.append('=');
            sb.append(((this.createdAt == null)?"<null>":this.createdAt));
            sb.append(',');
            sb.append("id");
            sb.append('=');
            sb.append(this.id);
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }

    }
}



