
package com.eng.wmdim.wmdimming.data;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GardenGateStateResponse implements Serializable
{

    @SerializedName("Gate")
    @Expose
    private int gate;
    @SerializedName("GateStatus")
    @Expose
    private String gateStatus;
    private final static long serialVersionUID = -2316232377087141329L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GardenGateStateResponse() {
    }

    /**
     * 
     * @param gateStatus
     * @param gate
     */
    public GardenGateStateResponse(int gate, String gateStatus) {
        super();
        this.gate = gate;
        this.gateStatus = gateStatus;
    }

    public int getGate() {
        return gate;
    }

    public void setGate(int gate) {
        this.gate = gate;
    }

    public String getGateStatus() {
        return gateStatus;
    }

    public void setGateStatus(String gateStatus) {
        this.gateStatus = gateStatus;
    }

}
