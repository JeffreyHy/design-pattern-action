package com.huang.pattern.observer.listener;

import org.springframework.util.Assert;

import java.util.EventObject;

/**
 * Abstract class to be extended by all mission events.
 * <p>
 * Created by JeffreyHy on 2017/12/20.
 */
public abstract class MissionEvent extends EventObject {
    private static final long serialVersionUID = 8685490906562121067L;

    /**
     * System time when the event happened
     */
    private final long timestamp;

    /**
     * the event type
     */
    private Byte eventType;

    /**
     * Create a new MissionEvent.
     *
     * @param source    the MissionContext that the event is raised for
     * @param eventType the event type that the event is raised for
     * @throws IllegalArgumentException if source or eventType is null.
     */
    public MissionEvent(MissionContext source, Byte eventType) {
        super(source);
        Assert.notNull(eventType, "eventType cannot null");
        this.eventType = eventType;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * Return the system time in milliseconds when the event happened.
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Get the MissionContext that the event was raised for.
     *
     * @return
     */
    public final MissionContext getMissionContext() {
        return (MissionContext) getSource();
    }

    /**
     * Get the event type that the event was raised for.
     *
     * @return
     */
    public Byte getEventType() {
        return eventType;
    }
}
