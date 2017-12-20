package com.huang.pattern.observer.listener;

import java.io.Serializable;

/**
 * Created by JeffreyHy on 2017/12/20.
 */
public class MissionContext implements Serializable{
    private static final long serialVersionUID = -1712852676277492151L;

    private Integer missionId;

    public Integer getMissionId() {
        return missionId;
    }

    public void setMissionId(Integer missionId) {
        this.missionId = missionId;
    }
}
