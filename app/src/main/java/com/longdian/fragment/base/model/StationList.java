package com.longdian.fragment.base.model;

import java.util.List;

/**
 * Created by phoenix on 2017/6/10.
 */

public class StationList {

    private List<StationData> stationDataList;
    private List<StandData> standDataList;
    private PriceData priceData;

    public List<StationData> getStationDataList() {
        return stationDataList;
    }

    public void setStationDataList(List<StationData> stationDataList) {
        this.stationDataList = stationDataList;
    }

    public List<StandData> getStandDataList() {
        return standDataList;
    }

    public void setStandDataList(List<StandData> standDataList) {
        this.standDataList = standDataList;
    }

    public PriceData getPriceData() {
        return priceData;
    }

    public void setPriceData(PriceData priceData) {
        this.priceData = priceData;
    }
}
