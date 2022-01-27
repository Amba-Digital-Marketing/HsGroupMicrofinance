package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Nullable;

public class AllUsers {
    @SerializedName("User Details")
    @Expose
    private List<UserDetail> userDetails = null;

    public List<UserDetail> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(List<UserDetail> userDetails) {
        this.userDetails = userDetails;
    }

    public class UserDetail {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("account_number")
        @Expose
        private String accountNumber;
        @SerializedName("balance")
        @Expose
        @Nullable
        private Double balance;


        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("UserDetail{");
            sb.append("id=").append(id);
            sb.append(", name='").append(name).append('\'');
            sb.append(", email='").append(email).append('\'');
            sb.append(", phone='").append(phone).append('\'');
            sb.append(", accountNumber='").append(accountNumber).append('\'');
            sb.append(", balance=").append(balance);
            sb.append('}');
            return sb.toString();
        }

        public Double getBalance() {
            return balance;
        }

        public void setBalance(Double balance) {
            this.balance = balance;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
