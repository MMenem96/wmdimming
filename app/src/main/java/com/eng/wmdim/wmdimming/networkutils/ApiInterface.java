package com.eng.wmdim.wmdimming.networkutils;

import com.eng.wmdim.wmdimming.data.GardenGateNewStateResponse;
import com.eng.wmdim.wmdimming.data.GardenGateStateResponse;
import com.eng.wmdim.wmdimming.data.GardenLightStateResponse;
import com.eng.wmdim.wmdimming.data.Light;
import com.eng.wmdim.wmdimming.data.Outdoors;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {


    //Get All outdoors
    @GET("/outdoor/getAll")
    Call<List<Outdoors>> getAllOutdoors();


    //Get All garden lights
    @GET("/outdoor/garden/lights/getAll")
    Call<List<Light>> getAllGardenLights();


    //Change light status
    @GET("/outdoor/garden/lights/{Integer}")
    Call<GardenLightStateResponse> changeLightsStatus(@Path("Integer") Integer lightId);

    @GET("/outdoor/garden/gate/getStatus")
    Call<GardenGateStateResponse> getGateStatus();


    @GET("/outdoor/garden/gate/{Integer}")
    Call<GardenGateNewStateResponse> changeGateStatus(@Path("Integer") Integer newStatusCode);


}
