package com.huang.pattern.observer.listener;

import java.util.Set;

/**
 * Interface that encapsulates event publication functionality.
 * <p>
 * Created by JeffreyHy on 2017/12/20.
 */
public interface MissionEventPublisher {
    /**
     * Notify all listeners registered with this mission of an mission event.
     *
     * @param event
     */
    void publishEvent(MissionEvent event);

    /**
     * register a listener to be notified of interest events.
     *
     * @param listener       the listener to register
     * @param interestEvents
     */
    void registerMissionListener(MissionListener listener, Set<EventTypeEnum> interestEvents);

    /**
     * deregister a listener from the notification list.
     *
     * @param listener the listener to deregister
     */
    void deregisterMissionListener(MissionListener listener);

    /**
     * Remove all listeners registered with this multicaster.
     */
    void removeAllListeners();
}
