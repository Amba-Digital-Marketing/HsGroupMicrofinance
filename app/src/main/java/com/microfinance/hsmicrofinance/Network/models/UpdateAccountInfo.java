package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Nullable;

public class UpdateAccountInfo {

    @SerializedName("0")
    @Expose
    private String _0;
    @SerializedName("Updated Details")
    @Expose
    private UpdatedDetails updatedDetails;

    public String get0() {
        return _0;
    }

    public void set0(String _0) {
        this._0 = _0;
    }

    public UpdatedDetails getUpdatedDetails() {
        return updatedDetails;
    }

    public void setUpdatedDetails(UpdatedDetails updatedDetails) {
        this.updatedDetails = updatedDetails;
    }
    public class UpdatedDetails {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("role_id")
        @Expose
        private Integer roleId;
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
        private Double balance;
        @SerializedName("amount")
        @Expose
        @Nullable
        private Double amount;
        @SerializedName("account_number")
        @Expose
        private String accountNumber;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("two_step_auth")
        @Expose
        @Nullable
        private Boolean twoStepAuth;
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
        @Nullable
        private Integer pin;

        @SerializedName("street")
        @Expose
        @Nullable
        private String street;

        @SerializedName("city")
        @Expose
        @Nullable
        private String city;
        @SerializedName("state")
        @Expose
        @Nullable
        private String state;
        @SerializedName("postal_code")
        @Expose
        @Nullable
        private String postalCode;
        @SerializedName("country")
        @Expose
        @Nullable
        private String country;
        @SerializedName("fcmtoken")
        @Expose
        @Nullable
        private String fcmToken;

        @Nullable
        public String getStreet() {
            return street;
        }

        public void setStreet(@Nullable String street) {
            this.street = street;
        }

        @Nullable
        public String getCity() {
            return city;
        }

        public void setCity(@Nullable String city) {
            this.city = city;
        }

        @Nullable
        public String getState() {
            return state;
        }

        public void setState(@Nullable String state) {
            this.state = state;
        }

        @Nullable
        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(@Nullable String postalCode) {
            this.postalCode = postalCode;
        }

        @Nullable
        public String getCountry() {
            return country;
        }

        public void setCountry(@Nullable String country) {
            this.country = country;
        }

        @Nullable
        public String getFcmToken() {
            return fcmToken;
        }

        public void setFcmToken(@Nullable String fcmToken) {
            this.fcmToken = fcmToken;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getRoleId() {
            return roleId;
        }

        public void setRoleId(Integer roleId) {
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

        public Double getBalance() {
            return balance;
        }

        public void setBalance(Double balance) {
            this.balance = balance;
        }

        @Nullable
        public Double getAmount() {
            return amount;
        }

        public void setAmount(@Nullable Double amount) {
            this.amount = amount;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        @Nullable
        public Boolean getTwoStepAuth() {
            return twoStepAuth;
        }

        public void setTwoStepAuth(@Nullable Boolean twoStepAuth) {
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

        @Nullable
        public Integer getPin() {
            return pin;
        }

        public void setPin(@Nullable Integer pin) {
            this.pin = pin;
        }

        @Override
        public String toString() {
            return "UpdatedDetails{" +
                    "id=" + id +
                    ", roleId=" + roleId +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", emailVerifiedAt='" + emailVerifiedAt + '\'' +
                    ", phone='" + phone + '\'' +
                    ", phoneVerifiedAt='" + phoneVerifiedAt + '\'' +
                    ", balance=" + balance +
                    ", amount=" + amount +
                    ", accountNumber='" + accountNumber + '\'' +
                    ", status=" + status +
                    ", twoStepAuth=" + twoStepAuth +
                    ", createdAt='" + createdAt + '\'' +
                    ", updatedAt='" + updatedAt + '\'' +
                    ", kra='" + kra + '\'' +
                    ", image='" + image + '\'' +
                    ", pin=" + pin +
                    ", street='" + street + '\'' +
                    ", city='" + city + '\'' +
                    ", state='" + state + '\'' +
                    ", postalCode='" + postalCode + '\'' +
                    ", country='" + country + '\'' +
                    ", fcmToken='" + fcmToken + '\'' +
                    '}';
        }
    }
}
