package com.microfinance.hsmicrofinance.Network.entity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WithdrawDetails {

    @SerializedName("Withdraw Method Details")
    @Expose
    private List<WithdrawMethodDetail> withdrawMethodDetails = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public WithdrawDetails() {
    }

    /**
     *
     * @param withdrawMethodDetails
     */
    public WithdrawDetails(List<WithdrawMethodDetail> withdrawMethodDetails) {
        super();
        this.withdrawMethodDetails = withdrawMethodDetails;
    }

    public List<WithdrawMethodDetail> getWithdrawMethodDetails() {
        return withdrawMethodDetails;
    }

    public void setWithdrawMethodDetails(List<WithdrawMethodDetail> withdrawMethodDetails) {
        this.withdrawMethodDetails = withdrawMethodDetails;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(WithdrawDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("withdrawMethodDetails");
        sb.append('=');
        sb.append(((this.withdrawMethodDetails == null)?"<null>":this.withdrawMethodDetails));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    public class Pivot {

        @SerializedName("withdrawmethod_id")
        @Expose
        private int withdrawmethodId;
        @SerializedName("term_id")
        @Expose
        private int termId;

        /**
         * No args constructor for use in serialization
         *
         */
        public Pivot() {
        }

        /**
         *
         * @param termId
         * @param withdrawmethodId
         */
        public Pivot(int withdrawmethodId, int termId) {
            super();
            this.withdrawmethodId = withdrawmethodId;
            this.termId = termId;
        }

        public int getWithdrawmethodId() {
            return withdrawmethodId;
        }

        public void setWithdrawmethodId(int withdrawmethodId) {
            this.withdrawmethodId = withdrawmethodId;
        }

        public int getTermId() {
            return termId;
        }

        public void setTermId(int termId) {
            this.termId = termId;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(Pivot.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("withdrawmethodId");
            sb.append('=');
            sb.append(this.withdrawmethodId);
            sb.append(',');
            sb.append("termId");
            sb.append('=');
            sb.append(this.termId);
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }

    }

    public class Term {

        @SerializedName("id")
        @Expose
        private int id;
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
        @SerializedName("pivot")
        @Expose
        private Pivot pivot;

        /**
         * No args constructor for use in serialization
         *
         */
        public Term() {
        }

        /**
         *
         * @param createdAt
         * @param featured
         * @param pivot
         * @param id
         * @param title
         * @param type
         * @param slug
         * @param status
         * @param updatedAt
         */
        public Term(int id, String title, String slug, String type, int status, int featured, String createdAt, String updatedAt, Pivot pivot) {
            super();
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public Pivot getPivot() {
            return pivot;
        }

        public void setPivot(Pivot pivot) {
            this.pivot = pivot;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(Term.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("id");
            sb.append('=');
            sb.append(this.id);
            sb.append(',');
            sb.append("title");
            sb.append('=');
            sb.append(((this.title == null)?"<null>":this.title));
            sb.append(',');
            sb.append("slug");
            sb.append('=');
            sb.append(((this.slug == null)?"<null>":this.slug));
            sb.append(',');
            sb.append("type");
            sb.append('=');
            sb.append(((this.type == null)?"<null>":this.type));
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
            sb.append(((this.createdAt == null)?"<null>":this.createdAt));
            sb.append(',');
            sb.append("updatedAt");
            sb.append('=');
            sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
            sb.append(',');
            sb.append("pivot");
            sb.append('=');
            sb.append(((this.pivot == null)?"<null>":this.pivot));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }

    }

    public class WithdrawMethodDetail {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("min_amount")
        @Expose
        private int minAmount;
        @SerializedName("max_amount")
        @Expose
        private int maxAmount;
        @SerializedName("charge_type")
        @Expose
        private String chargeType;
        @SerializedName("fix_charge")
        @Expose
        private int fixCharge;
        @SerializedName("percent_charge")
        @Expose
        private int percentCharge;
        @SerializedName("status")
        @Expose
        private int status;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("term")
        @Expose
        private List<Term> term = null;

        /**
         * No args constructor for use in serialization
         *
         */
        public WithdrawMethodDetail() {
        }

        /**
         *
         * @param minAmount
         * @param createdAt
         * @param chargeType
         * @param term
         * @param id
         * @param title
         * @param maxAmount
         * @param fixCharge
         * @param percentCharge
         * @param status
         * @param updatedAt
         */
        public WithdrawMethodDetail(int id, String title, int minAmount, int maxAmount, String chargeType, int fixCharge, int percentCharge, int status, String createdAt, String updatedAt, List<Term> term) {
            super();
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getMinAmount() {
            return minAmount;
        }

        public void setMinAmount(int minAmount) {
            this.minAmount = minAmount;
        }

        public int getMaxAmount() {
            return maxAmount;
        }

        public void setMaxAmount(int maxAmount) {
            this.maxAmount = maxAmount;
        }

        public String getChargeType() {
            return chargeType;
        }

        public void setChargeType(String chargeType) {
            this.chargeType = chargeType;
        }

        public int getFixCharge() {
            return fixCharge;
        }

        public void setFixCharge(int fixCharge) {
            this.fixCharge = fixCharge;
        }

        public int getPercentCharge() {
            return percentCharge;
        }

        public void setPercentCharge(int percentCharge) {
            this.percentCharge = percentCharge;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
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

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(WithdrawMethodDetail.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("id");
            sb.append('=');
            sb.append(this.id);
            sb.append(',');
            sb.append("title");
            sb.append('=');
            sb.append(((this.title == null)?"<null>":this.title));
            sb.append(',');
            sb.append("minAmount");
            sb.append('=');
            sb.append(this.minAmount);
            sb.append(',');
            sb.append("maxAmount");
            sb.append('=');
            sb.append(this.maxAmount);
            sb.append(',');
            sb.append("chargeType");
            sb.append('=');
            sb.append(((this.chargeType == null)?"<null>":this.chargeType));
            sb.append(',');
            sb.append("fixCharge");
            sb.append('=');
            sb.append(this.fixCharge);
            sb.append(',');
            sb.append("percentCharge");
            sb.append('=');
            sb.append(this.percentCharge);
            sb.append(',');
            sb.append("status");
            sb.append('=');
            sb.append(this.status);
            sb.append(',');
            sb.append("createdAt");
            sb.append('=');
            sb.append(((this.createdAt == null)?"<null>":this.createdAt));
            sb.append(',');
            sb.append("updatedAt");
            sb.append('=');
            sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
            sb.append(',');
            sb.append("term");
            sb.append('=');
            sb.append(((this.term == null)?"<null>":this.term));
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