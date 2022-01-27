package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WithdrawDetail {
    @SerializedName("Withdraw Method Details")
    @Expose
    public List<WithdrawMethodDetail> withdrawMethodDetails = null;

    public WithdrawDetail() {
    }

    public WithdrawDetail(List<WithdrawMethodDetail> withdrawMethodDetails) {
        this.withdrawMethodDetails = withdrawMethodDetails;
    }

    public List<WithdrawMethodDetail> getWithdrawMethodDetails() {
        return withdrawMethodDetails;
    }

    public void setWithdrawMethodDetails(List<WithdrawMethodDetail> withdrawMethodDetails) {
        this.withdrawMethodDetails = withdrawMethodDetails;
    }

    public class WithdrawMethodDetail {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("min_amount")
        @Expose
        public Integer minAmount;
        @SerializedName("max_amount")
        @Expose
        public Integer maxAmount;
        @SerializedName("charge_type")
        @Expose
        public String chargeType;
        @SerializedName("fix_charge")
        @Expose
        public Integer fixCharge;
        @SerializedName("percent_charge")
        @Expose
        public Integer percentCharge;
        @SerializedName("status")
        @Expose
        public Integer status;
        @SerializedName("created_at")
        @Expose
        public String createdAt;
        @SerializedName("updated_at")
        @Expose
        public String updatedAt;
        @SerializedName("term")
        @Expose
        public List<Term> term = null;

        @Override
        public String toString() {
            return "WithdrawMethodDetail{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", minAmount=" + minAmount +
                    ", maxAmount=" + maxAmount +
                    ", chargeType='" + chargeType + '\'' +
                    ", fixCharge=" + fixCharge +
                    ", percentCharge=" + percentCharge +
                    ", status=" + status +
                    ", createdAt='" + createdAt + '\'' +
                    ", updatedAt='" + updatedAt + '\'' +
                    ", term=" + term +
                    '}';
        }

        public WithdrawMethodDetail() {
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

        public Integer getMinAmount() {
            return minAmount;
        }

        public void setMinAmount(Integer minAmount) {
            this.minAmount = minAmount;
        }

        public Integer getMaxAmount() {
            return maxAmount;
        }

        public void setMaxAmount(Integer maxAmount) {
            this.maxAmount = maxAmount;
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

        public List<Term> getTerm() {
            return term;
        }

        public void setTerm(List<Term> term) {
            this.term = term;
        }

        public WithdrawMethodDetail(Integer id, String title, Integer minAmount, Integer maxAmount, String chargeType, Integer fixCharge, Integer percentCharge, Integer status, String createdAt, String updatedAt, List<Term> term) {
            this.id = id;
            this.title = title;
            this.minAmount = minAmount;
            this.maxAmount = maxAmount;
            this.chargeType = chargeType;
            this.fixCharge = fixCharge;
            this.percentCharge = percentCharge;
            this.status = status;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
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

            public class Pivot {

                @SerializedName("withdrawmethod_id")
                @Expose
                public Integer withdrawmethodId;
                @SerializedName("term_id")
                @Expose
                public Integer termId;

                public Pivot() {
                }

                public Pivot(Integer withdrawmethodId, Integer termId) {
                    this.withdrawmethodId = withdrawmethodId;
                    this.termId = termId;
                }

                @Override
                public String toString() {
                    return "Pivot{" +
                            "withdrawmethodId=" + withdrawmethodId +
                            ", termId=" + termId +
                            '}';
                }

                public Integer getWithdrawmethodId() {
                    return withdrawmethodId;
                }

                public void setWithdrawmethodId(Integer withdrawmethodId) {
                    this.withdrawmethodId = withdrawmethodId;
                }

                public Integer getTermId() {
                    return termId;
                }

                public void setTermId(Integer termId) {
                    this.termId = termId;
                }
            }

        }

    }
}
