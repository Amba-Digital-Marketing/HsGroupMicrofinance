package com.microfinance.hsmicrofinance.Network.entity;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currencies {

        @SerializedName("Currency Details")
        @Expose
        private List<CurrencyDetail> currencyDetails = null;


        public Currencies() {
        }

        /**
         * @param currencyDetails
         */
        public Currencies(List<CurrencyDetail> currencyDetails) {
            super();
            this.currencyDetails = currencyDetails;
        }

        public List<CurrencyDetail> getCurrencyDetails() {
            return currencyDetails;
        }

        public void setCurrencyDetails(List<CurrencyDetail> currencyDetails) {
            this.currencyDetails = currencyDetails;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(Currencies.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("currencyDetails");
            sb.append('=');
            sb.append(((this.currencyDetails == null) ? "<null>" : this.currencyDetails));
            sb.append(',');
            if (sb.charAt((sb.length() - 1)) == ',') {
                sb.setCharAt((sb.length() - 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }


    public class CurrencyDetail {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("status")
        @Expose
        private int status;
        @SerializedName("featured")
        @Expose
        private int featured;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        /**
         * No args constructor for use in serialization
         */
        public CurrencyDetail() {
        }

        /**
         * @param createdAt
         * @param featured
         * @param id
         * @param title
         * @param type
         * @param slug
         * @param status
         * @param updatedAt
         */
        public CurrencyDetail(String id, String title, String slug, String type, int status, int featured, String createdAt, String updatedAt) {
            super();
            this.id = id;
            this.title = title;
            this.slug = slug;
            this.type = type;
            this.status = status;
            this.featured = featured;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getFeatured() {
            return featured;
        }

        public void setFeatured(int featured) {
            this.featured = featured;
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

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(CurrencyDetail.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("id");
            sb.append('=');
            sb.append(this.id);
            sb.append(',');
            sb.append("title");
            sb.append('=');
            sb.append(((this.title == null) ? "<null>" : this.title));
            sb.append(',');
            sb.append("slug");
            sb.append('=');
            sb.append(((this.slug == null) ? "<null>" : this.slug));
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null) ? "<null>" : this.type));
            sb.append(',');
            sb.append("status");
            sb.append('=');
            sb.append(this.status);
            sb.append(',');
            sb.append("featured");
            sb.append('=');
            sb.append(this.featured);
            sb.append(',');
            sb.append("createdAt");
            sb.append('=');
            sb.append(((this.createdAt == null) ? "<null>" : this.createdAt));
            sb.append(',');
            sb.append("updatedAt");
            sb.append('=');
            sb.append(((this.updatedAt == null) ? "<null>" : this.updatedAt));
            sb.append(',');
            if (sb.charAt((sb.length() - 1)) == ',') {
                sb.setCharAt((sb.length() - 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }

    }
}