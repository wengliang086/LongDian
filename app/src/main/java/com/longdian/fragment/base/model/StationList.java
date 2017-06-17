package com.longdian.fragment.base.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phoenix on 2017/6/10.
 */

public class StationList {

    private List<StationData> stationDataList;
    private List<StandData> standDataList;
    private PriceData priceData;

    public List<StandData> getById(String stationId) {
        List<StandData> result = new ArrayList<>();
        for (StandData data : standDataList) {
            if (data.getStationId().equals(stationId)) {
                result.add(data);
            }
        }
        return result;
    }

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
