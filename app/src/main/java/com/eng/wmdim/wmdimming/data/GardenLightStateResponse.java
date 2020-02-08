
package com.eng.wmdim.wmdimming.data;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GardenLightStateResponse implements Serializable
{

    @SerializedName("lightStatus")
    @Expose
    private String lightStatus;
    private final static long serialVersionUID = -1846415994317803788L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GardenLightStateResponse() {
    }

    /**
     * 
     * @param lightStatus
     */
    public GardenLightStateResponse(String lightStatus) {
        super();
        this.lightStatus = lightStatus;
    }

    public String getLightStatus() {
        return lightStatus;
    }

    public void setLightStatus(String lightStatus) {
        this.lightStatus = lightStatus;
    }

}
