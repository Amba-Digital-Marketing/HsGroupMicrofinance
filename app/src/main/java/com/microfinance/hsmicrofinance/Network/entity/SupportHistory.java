package com.microfinance.hsmicrofinance.Network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SupportHistory {

    @SerializedName("All Support Requests Based On ID")
    @Expose
    private List<AllSupportRequestsBasedOnID> allSupportRequestsBasedOnID = null;


    public SupportHistory() {
    }


    public SupportHistory(List<AllSupportRequestsBasedOnID> allSupportRequestsBasedOnID) {
        super();
        this.allSupportRequestsBasedOnID = allSupportRequestsBasedOnID;
    }

    public List<AllSupportRequestsBasedOnID> getAllSupportRequestsBasedOnID() {
        return allSupportRequestsBasedOnID;
    }

    public void setAllSupportRequestsBasedOnID(List<AllSupportRequestsBasedOnID> allSupportRequestsBasedOnID) {
        this.allSupportRequestsBasedOnID = allSupportRequestsBasedOnID;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SupportHistory.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("allSupportRequestsBasedOnID");
        sb.append('=');
        sb.append(((this.allSupportRequestsBasedOnID == null)?"<null>":this.allSupportRequestsBasedOnID));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }




public class AllSupportRequestsBasedOnID {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("user_id")
    @Expose
    private int userId;
    @SerializedName("ticket_no")
    @Expose
    private String ticketNo;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;


    public AllSupportRequestsBasedOnID() {
    }


    public AllSupportRequestsBasedOnID(int id, int userId, String ticketNo, String title, int status, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.userId = userId;
        this.ticketNo = ticketNo;
        this.title = title;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AllSupportRequestsBasedOnID.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(this.id);
        sb.append(',');
        sb.append("userId");
        sb.append('=');
        sb.append(this.userId);
        sb.append(',');
        sb.append("ticketNo");
        sb.append('=');
        sb.append(((this.ticketNo == null) ? "<null>" : this.ticketNo));
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null) ? "<null>" : this.title));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(this.status);
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.createdAt == null) ? "<null>" : this.createdAt));
        sb.append(',');
        sb.append("updatedAt");
        sb.append('=');
        sb.append(((this.updatedAt == null) ? "<null>" : this.updatedAt));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
}