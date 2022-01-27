package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bills {


        @SerializedName("Bill Details")
        @Expose
        private BillDetails billDetails;
        @SerializedName("0")
        @Expose
        private String _0;

        public BillDetails getBillDetails() {
            return billDetails;
        }

        public void setBillDetails(BillDetails billDetails) {
            this.billDetails = billDetails;
        }

        public String get0() {
            return _0;
        }

        public void set0(String _0) {
            this._0 = _0;
        }

        public class BillDetails {

            @SerializedName("sender_id")
            @Expose
            private Integer senderId;
            @SerializedName("receiver_id")
            @Expose
            private Integer receiverId;
            @SerializedName("title")
            @Expose
            private String title;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("amount")
            @Expose
            private String amount;
            @SerializedName("status")
            @Expose
            private Integer status;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("id")
            @Expose
            private Integer id;

            @Override
            public String toString() {
                return "BillDetails{" +
                        "senderId=" + senderId +
                        ", receiverId=" + receiverId +
                        ", title='" + title + '\'' +
                        ", description='" + description + '\'' +
                        ", amount='" + amount + '\'' +
                        ", status=" + status +
                        ", updatedAt='" + updatedAt + '\'' +
                        ", createdAt='" + createdAt + '\'' +
                        ", id=" + id +
                        '}';
            }

            public Integer getSenderId() {
                return senderId;
            }

            public void setSenderId(Integer senderId) {
                this.senderId = senderId;
            }

            public Integer getReceiverId() {
                return receiverId;
            }

            public void setReceiverId(Integer receiverId) {
                this.receiverId = receiverId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public Integer getStatus() {
                return status;
            }

            public void setStatus(Integer status) {
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

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }
        }
}
