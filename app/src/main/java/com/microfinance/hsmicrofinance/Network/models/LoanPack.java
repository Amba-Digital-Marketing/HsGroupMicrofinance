package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoanPack {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("min_amount")
    @Expose
    public Integer minAmount;
    @SerializedName("max_amount")
    @Expose
    public Integer maxAmount;
    @SerializedName("duration")
    @Expose
    public Integer duration;
    @SerializedName("fixed_charged")
    @Expose
    public Integer fixedCharged;
    @SerializedName("percent_charged")
    @Expose
    public Integer percentCharged;
    @SerializedName("charge_type")
    @Expose
    public String chargeType;
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

    public LoanPack() {
    }

    public LoanPack(Integer id, String name, Integer minAmount, Integer maxAmount, Integer duration, Integer fixedCharged, Integer percentCharged, String chargeType, Integer status, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.duration = duration;
        this.fixedCharged = fixedCharged;
        this.percentCharged = percentCharged;
        this.chargeType = chargeType;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "LoanPackages{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", minAmount=" + minAmount +
                ", maxAmount=" + maxAmount +
                ", duration=" + duration +
                ", fixedCharged=" + fixedCharged +
                ", percentCharged=" + percentCharged +
                ", chargeType='" + chargeType + '\'' +
                ", status=" + status +
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getFixedCharged() {
        return fixedCharged;
    }

    public void setFixedCharged(Integer fixedCharged) {
        this.fixedCharged = fixedCharged;
    }

    public Integer getPercentCharged() {
        return percentCharged;
    }

    public void setPercentCharged(Integer percentCharged) {
        this.percentCharged = percentCharged;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
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
}
