package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DepositHistory {
    @SerializedName("deposits")
    @Expose
    private List<Deposit> deposits = null;

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    public class Deposit {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("trx")
        @Expose
        private String trx;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("getway_id")
        @Expose
        private Integer getwayId;
        @SerializedName("type")
        @Expose
        private String type;
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
        @SerializedName("getway")
        @Expose
        private Getway getway;

        @Override
        public String toString() {
            return "Deposit{" +
                    "id=" + id +
                    ", trx='" + trx + '\'' +
                    ", userId=" + userId +
                    ", getwayId=" + getwayId +
                    ", type='" + type + '\'' +
                    ", amount=" + amount +
                    ", charge=" + charge +
                    ", status=" + status +
                    ", createdAt='" + createdAt + '\'' +
                    ", updatedAt='" + updatedAt + '\'' +
                    ", getway=" + getway +
                    '}';
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTrx() {
            return trx;
        }

        public void setTrx(String trx) {
            this.trx = trx;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getGetwayId() {
            return getwayId;
        }

        public void setGetwayId(Integer getwayId) {
            this.getwayId = getwayId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public Getway getGetway() {
            return getway;
        }

        public void setGetway(Getway getway) {
            this.getway = getway;
        }

        public class Getway {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("logo")
            @Expose
            private String logo;
            @SerializedName("rate")
            @Expose
            private Double rate;
            @SerializedName("deposit_min")
            @Expose
            private Integer depositMin;
            @SerializedName("deposit_max")
            @Expose
            private Integer depositMax;
            @SerializedName("charge_type")
            @Expose
            private String chargeType;
            @SerializedName("fix_charge")
            @Expose
            private Integer fixCharge;
            @SerializedName("percent_charge")
            @Expose
            private Integer percentCharge;
            @SerializedName("type")
            @Expose
            private Integer type;
            @SerializedName("status")
            @Expose
            private Integer status;
            @SerializedName("data")
            @Expose
            private String data;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;

            @Override
            public String toString() {
                return "Getway{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", logo='" + logo + '\'' +
                        ", rate=" + rate +
                        ", depositMin=" + depositMin +
                        ", depositMax=" + depositMax +
                        ", chargeType='" + chargeType + '\'' +
                        ", fixCharge=" + fixCharge +
                        ", percentCharge=" + percentCharge +
                        ", type=" + type +
                        ", status=" + status +
                        ", data='" + data + '\'' +
                        ", createdAt='" + createdAt + '\'' +
                        ", updatedAt='" + updatedAt + '\'' +
                        '}';
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

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public Double getRate() {
                return rate;
            }

            public void setRate(Double rate) {
                this.rate = rate;
            }

            public Integer getDepositMin() {
                return depositMin;
            }

            public void setDepositMin(Integer depositMin) {
                this.depositMin = depositMin;
            }

            public Integer getDepositMax() {
                return depositMax;
            }

            public void setDepositMax(Integer depositMax) {
                this.depositMax = depositMax;
            }

            public String getChargeType() {
                return chargeType;
            }

            public void setChargeType(String chargeType) {
                this.chargeType = chargeType;
            }

            public Integer getFixCharge() {
                return fixCharge;
            }

            public void setFixCharge(Integer fixCharge) {
                this.fixCharge = fixCharge;
            }

            public Integer getPercentCharge() {
                return percentCharge;
            }

            public void setPercentCharge(Integer percentCharge) {
                this.percentCharge = percentCharge;
            }

            public Integer getType() {
                return type;
            }

            public void setType(Integer type) {
                this.type = type;
            }

            public Integer getStatus() {
                return status;
            }

            public void setStatus(Integer status) {
                this.status = status;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
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

        }

    }
}
