package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Nullable;

public class BillStatement {
    @SerializedName("Bill Statement Details")
    @Expose
    private List<BillStatementDetail> billStatementDetails = null;

    public List<BillStatementDetail> getBillStatementDetails() {
        return billStatementDetails;
    }

    public void setBillStatementDetails(List<BillStatementDetail> billStatementDetails) {
        this.billStatementDetails = billStatementDetails;
    }

    public class BillStatementDetail {

        @SerializedName("id")
        @Expose
        private Integer id;
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
        private Double amount;
        @SerializedName("charge")
        @Expose
        private Double charge;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("sender")
        @Expose
        private Sender sender;
        @SerializedName("receiver")
        @Expose
        private Receiver receiver;

        @Override
        public String toString() {
            return "BillStatementDetail{" +
                    "id=" + id +
                    ", senderId=" + senderId +
                    ", receiverId=" + receiverId +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", amount=" + amount +
                    ", charge=" + charge +
                    ", status=" + status +
                    ", createdAt='" + createdAt + '\'' +
                    ", updatedAt='" + updatedAt + '\'' +
                    ", sender=" + sender +
                    ", receiver=" + receiver +
                    '}';
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public Double getCharge() {
            return charge;
        }

        public void setCharge(Double charge) {
            this.charge = charge;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
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

        public Sender getSender() {
            return sender;
        }

        public void setSender(Sender sender) {
            this.sender = sender;
        }

        public Receiver getReceiver() {
            return receiver;
        }

        public void setReceiver(Receiver receiver) {
            this.receiver = receiver;
        }

        public class Receiver {

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
            private Double amount;
            @SerializedName("account_number")
            @Expose
            private String accountNumber;
            @SerializedName("status")
            @Expose
            private Integer status;
            @SerializedName("two_step_auth")
            @Expose
            private Integer twoStepAuth;
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

            @Override
            public String toString() {
                return "Receiver{" +
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
                        '}';
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

            public Double getAmount() {
                return amount;
            }

            public void setAmount(Double amount) {
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

            public Integer getTwoStepAuth() {
                return twoStepAuth;
            }

            public void setTwoStepAuth(Integer twoStepAuth) {
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

            public Integer getPin() {
                return pin;
            }

            public void setPin(Integer pin) {
                this.pin = pin;
            }
        }

        public class Sender {

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
            private Integer balance;
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
            private Integer twoStepAuth;
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

            @Override
            public String toString() {
                return "Sender{" +
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
                        '}';
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

            public Integer getBalance() {
                return balance;
            }

            public void setBalance(Integer balance) {
                this.balance = balance;
            }

            public Double getAmount() {
                return amount;
            }

            public void setAmount(Double amount) {
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

            public Integer getTwoStepAuth() {
                return twoStepAuth;
            }

            public void setTwoStepAuth(Integer twoStepAuth) {
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

            public Integer getPin() {
                return pin;
            }

            public void setPin(Integer pin) {
                this.pin = pin;
            }
        }
    }
}
