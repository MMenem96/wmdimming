
package com.eng.wmdim.wmdimming.data;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Light implements Serializable
{

    @SerializedName("lightId")
    @Expose
    private int lightId;
    @SerializedName("lightStatus")
    @Expose
    private int lightStatus;
    private final static long serialVersionUID = -7580699330549842935L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Light() {
    }

    /**
     *
     * @param lightStatus
     * @param lightId
     */
    public Light(int lightId, int lightStatus) {
        super();
        this.lightId = lightId;
        this.lightStatus = lightStatus;
    }

    public int getLightId() {
        return lightId;
    }

    public void setLightId(int lightId) {
        this.lightId = lightId;
    }

    public int getLightStatus() {
        return lightStatus;
    }

    public void setLightStatus(int lightStatus) {
        this.lightStatus = lightStatus;
    }

}
