package com.microfinance.hsmicrofinance.Network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

        @SerializedName("user")
        @Expose
        private User user;
        @SerializedName("token")
        @Expose
        private String token;

        @SerializedName("pin_status")
        @Expose
        private String pin_status;


        public LoginResponse() {
        }

        public LoginResponse(User user, String token,String pin_status) {
            super();
            this.user = user;
            this.token = token;
            this.pin_status =pin_status;
        }

    public String getPin_status() {
        return pin_status;
    }

    public void setPin_status(String pin_status) {
        this.pin_status = pin_status;
    }

    public User getUser() {
            return user;
        }

        public void setUser(User user) {
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
            sb.append(LoginResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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


    public class User {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("role_id")
        @Expose
        private int roleId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("email_verified_at")
        @Expose
        private String emailVerifiedAt;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("phone_verified_at")
        @Expose
        private String phoneVerifiedAt;
        @SerializedName("balance")
        @Expose
        private double balance;
        @SerializedName("amount")
        @Expose
        private Object amount;
        @SerializedName("account_number")
        @Expose
        private String accountNumber;
        @SerializedName("status")
        @Expose
        private int status;
        @SerializedName("two_step_auth")
        @Expose
        private int twoStepAuth;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("kra")
        @Expose
        private String kra;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("pin")
        @Expose
        private Object pin;

        public User() {
        }

        public User(int id, int roleId, String name, String email, String emailVerifiedAt, String phone, String phoneVerifiedAt, double balance, Object amount, String accountNumber, int status, int twoStepAuth, String createdAt, String updatedAt, String kra, String image, Object pin) {
            super();
            this.id = id;
            this.roleId = roleId;
            this.name = name;
            this.email = email;
            this.emailVerifiedAt = emailVerifiedAt;
            this.phone = phone;
            this.phoneVerifiedAt = phoneVerifiedAt;
            this.balance = balance;
            this.amount = amount;
            this.accountNumber = accountNumber;
            this.status = status;
            this.twoStepAuth = twoStepAuth;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.kra = kra;
            this.image = image;
            this.pin = pin;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
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

        public String getEmailVerifiedAt() {
            return emailVerifiedAt;
        }

        public void setEmailVerifiedAt(String emailVerifiedAt) {
            this.emailVerifiedAt = emailVerifiedAt;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPhoneVerifiedAt() {
            return phoneVerifiedAt;
        }

        public void setPhoneVerifiedAt(String phoneVerifiedAt) {
            this.phoneVerifiedAt = phoneVerifiedAt;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public Object getAmount() {
            return amount;
        }

        public void setAmount(Object amount) {
            this.amount = amount;
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

        public int getTwoStepAuth() {
            return twoStepAuth;
        }

        public void setTwoStepAuth(int twoStepAuth) {
            this.twoStepAuth = twoStepAuth;
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

        public String getKra() {
            return kra;
        }

        public void setKra(String kra) {
            this.kra = kra;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Object getPin() {
            return pin;
        }

        public void setPin(Object pin) {
            this.pin = pin;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(User.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("id");
            sb.append('=');
            sb.append(this.id);
            sb.append(',');
            sb.append("roleId");
            sb.append('=');
            sb.append(this.roleId);
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("email");
            sb.append('=');
            sb.append(((this.email == null)?"<null>":this.email));
            sb.append(',');
            sb.append("emailVerifiedAt");
            sb.append('=');
            sb.append(((this.emailVerifiedAt == null)?"<null>":this.emailVerifiedAt));
            sb.append(',');
            sb.append("phone");
            sb.append('=');
            sb.append(((this.phone == null)?"<null>":this.phone));
            sb.append(',');
            sb.append("phoneVerifiedAt");
            sb.append('=');
            sb.append(((this.phoneVerifiedAt == null)?"<null>":this.phoneVerifiedAt));
            sb.append(',');
            sb.append("balance");
            sb.append('=');
            sb.append(this.balance);
            sb.append(',');
            sb.append("amount");
            sb.append('=');
            sb.append(((this.amount == null)?"<null>":this.amount));
            sb.append(',');
            sb.append("accountNumber");
            sb.append('=');
            sb.append(((this.accountNumber == null)?"<null>":this.accountNumber));
            sb.append(',');
            sb.append("status");
            sb.append('=');
            sb.append(this.status);
            sb.append(',');
            sb.append("twoStepAuth");
            sb.append('=');
            sb.append(this.twoStepAuth);
            sb.append(',');
            sb.append("createdAt");
            sb.append('=');
            sb.append(((this.createdAt == null)?"<null>":this.createdAt));
            sb.append(',');
            sb.append("updatedAt");
            sb.append('=');
            sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
            sb.append(',');
            sb.append("kra");
            sb.append('=');
            sb.append(((this.kra == null)?"<null>":this.kra));
            sb.append(',');
            sb.append("image");
            sb.append('=');
            sb.append(((this.image == null)?"<null>":this.image));
            sb.append(',');
            sb.append("pin");
            sb.append('=');
            sb.append(((this.pin == null)?"<null>":this.pin));
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
