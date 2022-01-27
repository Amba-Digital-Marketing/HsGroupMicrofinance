package com.microfinance.hsmicrofinance.Network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InvestPlans {
    @SerializedName("investment packages")
    @Expose
    private List<InvestmentPackage> investmentPackages = null;

    public List<InvestmentPackage> getInvestmentPackages() {
        return investmentPackages;
    }

    public void setInvestmentPackages(List<InvestmentPackage> investmentPackages) {
        this.investmentPackages = investmentPackages;
    }


        public class InvestmentPackage {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("title")
            @Expose
            private String title;
            @SerializedName("min_amount")
            @Expose
            private Integer minAmount;
            @SerializedName("max_amount")
            @Expose
            private Integer maxAmount;
            @SerializedName("duration")
            @Expose
            private Integer duration;
            @SerializedName("percent_return")
            @Expose
            private Integer percentReturn;
            @SerializedName("status")
            @Expose
            private Integer status;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;

            public InvestmentPackage() {
            }

            public InvestmentPackage(Integer id, String title, Integer minAmount, Integer maxAmount, Integer duration, Integer percentReturn, Integer status, String createdAt, String updatedAt) {
                this.id = id;
                this.title = title;
                this.minAmount = minAmount;
                this.maxAmount = maxAmount;
                this.duration = duration;
                this.percentReturn = percentReturn;
                this.status = status;
                this.createdAt = createdAt;
                this.updatedAt = updatedAt;
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

            public Integer getDuration() {
                return duration;
            }

            public void setDuration(Integer duration) {
                this.duration = duration;
            }

            public Integer getPercentReturn() {
                return percentReturn;
            }

            public void setPercentReturn(Integer percentReturn) {
                this.percentReturn = percentReturn;
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

            @Override
            public String toString() {
                return "InvestmentPackage{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", minAmount=" + minAmount +
                        ", maxAmount=" + maxAmount +
                        ", duration=" + duration +
                        ", percentReturn=" + percentReturn +
                        ", status=" + status +
                        ", createdAt='" + createdAt + '\'' +
                        ", updatedAt='" + updatedAt + '\'' +
                        '}';
            }
        }
}
