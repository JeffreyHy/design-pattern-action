package com.huang.pattern.observer.listener;

/**
 * Event raised when an Mission gets published.
 * <p>
 * Created by JeffreyHy on 2017/12/20.
 */
public class MissionPublishEvent extends MissionEvent {
    /**
     * Creates a new MissionPublishedEvent.
     *
     * @param source the MissionContext that has been published
     * @throws IllegalArgumentException if source is null.
     */
    public MissionPublishEvent(MissionContext source) {
        super(source, EventTypeEnum.RELEASE.getCode());
    }
}
