package com.microfinance.hsmicrofinance.Network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AccountExtraInfoResponse {



        @SerializedName("User Account Adress Update Details")
        @Expose
        private List<UserAccountAdressUpdateDetail> userAccountAdressUpdateDetails = null;

        /**
         * No args constructor for use in serialization
         *
         */
        public AccountExtraInfoResponse() {
        }
        /**
         *
         * @param userAccountAdressUpdateDetails
         */
        public AccountExtraInfoResponse(List<UserAccountAdressUpdateDetail> userAccountAdressUpdateDetails) {
            super();
            this.userAccountAdressUpdateDetails = userAccountAdressUpdateDetails;
        }

        public List<UserAccountAdressUpdateDetail> getUserAccountAdressUpdateDetails() {
            return userAccountAdressUpdateDetails;
        }

        public void setUserAccountAdressUpdateDetails(List<UserAccountAdressUpdateDetail> userAccountAdressUpdateDetails) {
            this.userAccountAdressUpdateDetails = userAccountAdressUpdateDetails;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(AccountExtraInfoResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("userAccountAdressUpdateDetails");
            sb.append('=');
            sb.append(((this.userAccountAdressUpdateDetails == null)?"<null>":this.userAccountAdressUpdateDetails));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }





    public class UserAccountAdressUpdateDetail {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("street")
        @Expose
        private Object street;
        @SerializedName("city")
        @Expose
        private Object city;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("postal_code")
        @Expose
        private Object postalCode;
        @SerializedName("country")
        @Expose
        private Object country;

        /**
         * No args constructor for use in serialization
         *
         */
        public UserAccountAdressUpdateDetail() {
        }

        /**
         *
         * @param country
         * @param phone
         * @param city
         * @param street
         * @param postalCode
         * @param name
         * @param id
         * @param state
         * @param email
         */
        public UserAccountAdressUpdateDetail(int id, String name, String email, String phone, Object street, Object city, String state, Object postalCode, Object country) {
            super();
            this.id = id;
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.street = street;
            this.city = city;
            this.state = state;
            this.postalCode = postalCode;
            this.country = country;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public Object getStreet() {
            return street;
        }

        public void setStreet(Object street) {
            this.street = street;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Object getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(Object postalCode) {
            this.postalCode = postalCode;
        }

        public Object getCountry() {
            return country;
        }

        public void setCountry(Object country) {
            this.country = country;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(UserAccountAdressUpdateDetail.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("id");
            sb.append('=');
            sb.append(this.id);
            sb.append(',');
            sb.append("name");
            sb.append('=');
            sb.append(((this.name == null)?"<null>":this.name));
            sb.append(',');
            sb.append("email");
            sb.append('=');
            sb.append(((this.email == null)?"<null>":this.email));
            sb.append(',');
            sb.append("phone");
            sb.append('=');
            sb.append(((this.phone == null)?"<null>":this.phone));
            sb.append(',');
            sb.append("street");
            sb.append('=');
            sb.append(((this.street == null)?"<null>":this.street));
            sb.append(',');
            sb.append("city");
            sb.append('=');
            sb.append(((this.city == null)?"<null>":this.city));
            sb.append(',');
            sb.append("state");
            sb.append('=');
            sb.append(((this.state == null)?"<null>":this.state));
            sb.append(',');
            sb.append("postalCode");
            sb.append('=');
            sb.append(((this.postalCode == null)?"<null>":this.postalCode));
            sb.append(',');
            sb.append("country");
            sb.append('=');
            sb.append(((this.country == null)?"<null>":this.country));
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
