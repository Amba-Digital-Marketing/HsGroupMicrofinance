package com.microfinance.hsmicrofinance.Network.entity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SupportResponse {

        @SerializedName("0")
        @Expose
        private String _0;
        @SerializedName("Support Details Sent")
        @Expose
        private SupportDetailsSent supportDetailsSent;

        /**
         * No args constructor for use in serialization
         *
         */
        public SupportResponse() {
        }

        /**
         *
         * @param _0
         * @param supportDetailsSent
         */
        public SupportResponse(String _0, SupportDetailsSent supportDetailsSent) {
            super();
            this._0 = _0;
            this.supportDetailsSent = supportDetailsSent;
        }

        public String get0() {
            return _0;
        }

        public void set0(String _0) {
            this._0 = _0;
        }

        public SupportDetailsSent getSupportDetailsSent() {
            return supportDetailsSent;
        }

        public void setSupportDetailsSent(SupportDetailsSent supportDetailsSent) {
            this.supportDetailsSent = supportDetailsSent;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(SupportResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
            sb.append("_0");
            sb.append('=');
            sb.append(((this._0 == null)?"<null>":this._0));
            sb.append(',');
            sb.append("supportDetailsSent");
            sb.append('=');
            sb.append(((this.supportDetailsSent == null)?"<null>":this.supportDetailsSent));
            sb.append(',');
            if (sb.charAt((sb.length()- 1)) == ',') {
                sb.setCharAt((sb.length()- 1), ']');
            } else {
                sb.append(']');
            }
            return sb.toString();
        }



public class SupportDetailsSent {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("ticket_no")
    @Expose
    private String ticketNo;
    @SerializedName("user_id")
    @Expose
    private int userId;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private int id;

    /**
     * No args constructor for use in serialization
     *
     */
    public SupportDetailsSent() {
    }

    /**
     *
     * @param createdAt
     * @param ticketNo
     * @param id
     * @param title
     * @param userId
     * @param updatedAt
     */
    public SupportDetailsSent(String title, String ticketNo, int userId, String updatedAt, String createdAt, int id) {
        super();
        this.title = title;
        this.ticketNo = ticketNo;
        this.userId = userId;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SupportDetailsSent.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null)?"<null>":this.title));
        sb.append(',');
        sb.append("ticketNo");
        sb.append('=');
        sb.append(((this.ticketNo == null)?"<null>":this.ticketNo));
        sb.append(',');
        sb.append("userId");
        sb.append('=');
        sb.append(this.userId);
        sb.append(',');
        sb.append("updatedAt");
        sb.append('=');
        sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.createdAt == null)?"<null>":this.createdAt));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(this.id);
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
