package com.longdian.fragment.base.model;

import com.longdian.bean.BaseEntity;

public class StandData extends BaseEntity {
    /**
     * 机组表
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String standId;//机组ID
    private String standName;//机组名称
    private String stationId;//换热站ID
    private Integer designArea;//设计面积
    private Integer realArea;//实际面积

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStandId() {
        return standId;
    }

    public void setStandId(String standId) {
        this.standId = standId;
    }

    public String getStandName() {
        return standName;
    }

    public void setStandName(String standName) {
        this.standName = standName;
    }


    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public Integer getDesignArea() {
        return designArea;
    }

    public void setDesignArea(Integer designArea) {
        this.designArea = designArea;
    }

    public Integer getRealArea() {
        return realArea;
    }

    public void setRealArea(Integer realArea) {
        this.realArea = realArea;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("StandData [id=");
        builder.append(id);
        builder.append(", standId=");
        builder.append(standId);
        builder.append(", standName=");
        builder.append(standName);
        builder.append(", StationId=");
        builder.append(stationId);
        builder.append(", designArea=");
        builder.append(designArea);
        builder.append(", realArea=");
        builder.append(realArea);
        builder.append("]");
        return builder.toString();
    }
}
