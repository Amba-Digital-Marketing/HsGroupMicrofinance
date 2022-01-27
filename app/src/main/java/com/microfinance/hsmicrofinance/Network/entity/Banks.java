package com.microfinance.hsmicrofinance.Network.entity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Banks {

    @SerializedName("Bank Details")
    @Expose
    private List<BankDetail> bankDetails = null;

    public Banks() { }

    public Banks(List<BankDetail> bankDetails) {
        super();
        this.bankDetails = bankDetails;
    }

    public List<BankDetail> getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(List<BankDetail> bankDetails) {
        this.bankDetails = bankDetails;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Banks.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("bankDetails");
        sb.append('=');
        sb.append(((this.bankDetails == null)?"<null>":this.bankDetails));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }


public class BankDetail {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("period")
    @Expose
    private String period;
    @SerializedName("min_amount")
    @Expose
    private String minAmount;
    @SerializedName("max_amount")
    @Expose
    private String maxAmount;
    @SerializedName("fix_charge")
    @Expose
    private String fixCharge;
    @SerializedName("percent_charge")
    @Expose
    private String percentCharge;
    @SerializedName("charge_type")
    @Expose
    private String chargeType;
    @SerializedName("term_id")
    @Expose
    private String termId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;


    public BankDetail() {
    }

    public BankDetail(String id, String name, String period, String minAmount, String maxAmount, String fixCharge, String percentCharge, String chargeType, String termId, String status, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.period = period;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.fixCharge = fixCharge;
        this.percentCharge = percentCharge;
        this.chargeType = chargeType;
        this.termId = termId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "BankDetail{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", period='" + period + '\'' +
                ", minAmount='" + minAmount + '\'' +
                ", maxAmount='" + maxAmount + '\'' +
                ", fixCharge='" + fixCharge + '\'' +
                ", percentCharge='" + percentCharge + '\'' +
                ", chargeType='" + chargeType + '\'' +
                ", termId='" + termId + '\'' +
                ", status='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(String minAmount) {
        this.minAmount = minAmount;
    }

    public String getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(String maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getFixCharge() {
        return fixCharge;
    }

    public void setFixCharge(String fixCharge) {
        this.fixCharge = fixCharge;
    }

    public String getPercentCharge() {
        return percentCharge;
    }

    public void setPercentCharge(String percentCharge) {
        this.percentCharge = percentCharge;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
}
}
