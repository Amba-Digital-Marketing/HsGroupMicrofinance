package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentGateWays {
    @SerializedName("Edepost Gateways")
    @Expose
    public List<EdepostGateway> edepostGateways = null;

    public PaymentGateWays() {
    }

    public List<EdepostGateway> getEdepostGateways() {
        return edepostGateways;
    }

    public void setEdepostGateways(List<EdepostGateway> edepostGateways) {
        this.edepostGateways = edepostGateways;
    }

    public class EdepostGateway {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("logo")
        @Expose
        public String logo;
        @SerializedName("rate")
        @Expose
        public Double rate;
        @SerializedName("deposit_min")
        @Expose
        public Integer depositMin;
        @SerializedName("deposit_max")
        @Expose
        public Integer depositMax;
        @SerializedName("charge_type")
        @Expose
        public String chargeType;
        @SerializedName("fix_charge")
        @Expose
        public Double fixCharge;
        @SerializedName("percent_charge")
        @Expose
        public Double percentCharge;
        @SerializedName("type")
        @Expose
        public Integer type;
        @SerializedName("status")
        @Expose
        public Integer status;
        @SerializedName("data")
        @Expose
        public String data;
        @SerializedName("created_at")
        @Expose
        public String createdAt;
        @SerializedName("updated_at")
        @Expose
        public String updatedAt;
        @SerializedName("term")
        @Expose
        public List<Term> term = null;

        public EdepostGateway() {
        }

        @Override
        public String toString() {
            return "EdepostGateway{" +
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
                    ", term=" + term +
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

        public Double getFixCharge() {
            return fixCharge;
        }

        public void setFixCharge(Double fixCharge) {
            this.fixCharge = fixCharge;
        }

        public Double getPercentCharge() {
            return percentCharge;
        }

        public void setPercentCharge(Double percentCharge) {
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

        public List<Term> getTerm() {
            return term;
        }

        public void setTerm(List<Term> term) {
            this.term = term;
        }

        public class Term {

            @SerializedName("id")
            @Expose
            public Integer id;
            @SerializedName("title")
            @Expose
            public String title;
            @SerializedName("slug")
            @Expose
            public String slug;
            @SerializedName("type")
            @Expose
            public String type;
            @SerializedName("status")
            @Expose
            public Integer status;
            @SerializedName("featured")
            @Expose
            public Integer featured;
            @SerializedName("created_at")
            @Expose
            public String createdAt;
            @SerializedName("updated_at")
            @Expose
            public String updatedAt;
            @SerializedName("pivot")
            @Expose
            public Pivot pivot;

            public Term() {
            }

            @Override
            public String toString() {
                return "Term{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", slug='" + slug + '\'' +
                        ", type='" + type + '\'' +
                        ", status=" + status +
                        ", featured=" + featured +
                        ", createdAt='" + createdAt + '\'' +
                        ", updatedAt='" + updatedAt + '\'' +
                        ", pivot=" + pivot +
                        '}';
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
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

            public Integer getStatus() {
                return status;
            }

            public void setStatus(Integer status) {
                this.status = status;
            }

            public Integer getFeatured() {
                return featured;
            }

            public void setFeatured(Integer featured) {
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

            public Pivot getPivot() {
                return pivot;
            }

            public void setPivot(Pivot pivot) {
                this.pivot = pivot;
            }

            public Term(Integer id, String title, String slug, String type, Integer status, Integer featured, String createdAt, String updatedAt, Pivot pivot) {
                this.id = id;
                this.title = title;
                this.slug = slug;
                this.type = type;
                this.status = status;
                this.featured = featured;
                this.createdAt = createdAt;
                this.updatedAt = updatedAt;
                this.pivot = pivot;
            }

            public class Pivot {

                @SerializedName("getway_id")
                @Expose
                public Integer getwayId;
                @SerializedName("term_id")
                @Expose
                public Integer termId;

                @Override
                public String toString() {
                    return "Pivot{" +
                            "getwayId=" + getwayId +
                            ", termId=" + termId +
                            '}';
                }

                public Pivot() {
                }

                public Pivot(Integer getwayId, Integer termId) {
                    this.getwayId = getwayId;
                    this.termId = termId;
                }
            }

        }

    }
}
