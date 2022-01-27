package com.microfinance.hsmicrofinance.Network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddCommentSupportResponse {
        @SerializedName("0")
        @Expose
        private String _0;
        @SerializedName("Details")
        @Expose
        private Details details;

        /**
         * No args constructor for use in serialization
         *
         */
        public AddCommentSupportResponse() {
        }

        /**
         *
         * @param _0
         * @param details
         */
        public AddCommentSupportResponse(String _0, Details details) {
            super();
            this._0 = _0;
            this.details = details;
        }

        public String get0() {
            return _0;
        }

        public void set0(String _0) {
            this._0 = _0;
        }

        public Details getDetails() {
            return details;
        }

        public void setDetails(Details details) {
            this.details = details;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(AddCommentSupportResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("_0");
            sb.append('=');
            sb.append(((this._0 == null)?"<null>":this._0));
            sb.append(',');
            sb.append("details");
            sb.append('=');
            sb.append(((this.details == null)?"<null>":this.details));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }

    public class Details {

        @SerializedName("support_id")
        @Expose
        private String supportId;
        @SerializedName("comment")
        @Expose
        private String comment;
        @SerializedName("type")
        @Expose
        private int type;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("id")
        @Expose
        private int id;

        /**
         * No args constructor for use in serialization
         *
         */
        public Details() {
        }

        /**
         *
         * @param createdAt
         * @param comment
         * @param supportId
         * @param id
         * @param type
         * @param updatedAt
         */
        public Details(String supportId, String comment, int type, String updatedAt, String createdAt, int id) {
            super();
            this.supportId = supportId;
            this.comment = comment;
            this.type = type;
            this.updatedAt = updatedAt;
            this.createdAt = createdAt;
            this.id = id;
        }

        public String getSupportId() {
            return supportId;
        }

        public void setSupportId(String supportId) {
            this.supportId = supportId;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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
            sb.append(Details.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("supportId");
            sb.append('=');
            sb.append(((this.supportId == null)?"<null>":this.supportId));
            sb.append(',');
            sb.append("comment");
            sb.append('=');
            sb.append(((this.comment == null)?"<null>":this.comment));
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(this.type);
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
