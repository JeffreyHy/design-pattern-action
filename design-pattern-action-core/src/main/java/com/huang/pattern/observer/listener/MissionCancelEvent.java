package com.huang.pattern.observer.listener;

/**
 * Event raised when an Mission gets canceled.
 * <p>
 * Created by JeffreyHy on 2017/12/20.
 */
public class MissionCancelEvent extends MissionEvent {
    /**
     * Creates a new MissionCanceledEvent.
     *
     * @param source the MissionContext that the event is raised for
     * @throws IllegalArgumentException if source is null.
     */
    public MissionCancelEvent(MissionContext source) {
        super(source, EventTypeEnum.CANCEL.getCode());
    }
}
