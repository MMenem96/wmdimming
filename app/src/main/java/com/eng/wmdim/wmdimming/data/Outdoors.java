package com.eng.wmdim.wmdimming.data;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Outdoors implements Serializable {

    @SerializedName("outdoorId")
    @Expose
    private int outdoorId;
    @SerializedName("outdoorName")
    @Expose
    private String outdoorName;
    private final static long serialVersionUID = -8795902408729200772L;

    /**
     * No args constructor for use in serialization
     */
    public Outdoors() {
    }

    /**
     * @param outdoorId
     * @param outdoorName
     */
    public Outdoors(int outdoorId, String outdoorName) {
        super();
        this.outdoorId = outdoorId;
        this.outdoorName = outdoorName;
    }

    public int getOutdoorId() {
        return outdoorId;
    }

    public void setOutdoorId(int outdoorId) {
        this.outdoorId = outdoorId;
    }

    public String getOutdoorName() {
        return outdoorName;
    }

    public void setOutdoorName(String outdoorName) {
        this.outdoorName = outdoorName;
    }

}