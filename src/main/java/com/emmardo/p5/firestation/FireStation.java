package com.emmardo.p5.firestation;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FireStation {

    private String address;
    private String station;

    public FireStation() {

        this.address = "";
        this.station = "";
    }

    public FireStation(String address, String station){

        this.address = address;
        this.station = station;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public String getStation() {
        return station;
    }
    public void setStation(String station) {
        this.station = station;
    }

}
