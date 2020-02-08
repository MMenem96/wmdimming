
package com.eng.wmdim.wmdimming.data;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GardenGateNewStateResponse implements Serializable
{

    @SerializedName("gate")
    @Expose
    private String gate;
    private final static long serialVersionUID = -5058263423247522084L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GardenGateNewStateResponse() {
    }

    /**
     * 
     * @param gate
     */
    public GardenGateNewStateResponse(String gate) {
        super();
        this.gate = gate;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

}
