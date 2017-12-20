package com.huang.pattern.observer.listener;

import java.util.EventListener;

/**
 * Interface to be implemented by mission event listeners.
 * Based on the standard {@code java.util.EventListener} interface
 * for the Observer design pattern.
 * <p>
 * Created by JeffreyHy on 2017/12/20.
 */
public interface MissionListener<E extends MissionEvent> extends EventListener {
    /**
     * Handle an event
     *
     * @param event
     */
    void handleEvent(E event);
}
