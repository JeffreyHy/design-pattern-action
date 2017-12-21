package com.huang.pattern.observer.listener;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * Generic MissionEventPublisher implementation
 * <p>
 * Created by JeffreyHy on 2017/12/20.
 */
@Component
public class GenericMissionEventPublisher implements MissionEventPublisher {

    private final Map<Byte, Set<MissionListener>> listenerMap = Maps.newConcurrentMap();

    @Override
    public void publishEvent(MissionEvent event) {
        Set<MissionListener> listeners = listenerMap.get(event.getEventType());
        if (CollectionUtils.isNotEmpty(listeners)) {
            listeners.forEach(listener -> {
                listener.handleEvent(event);
            });
        }
    }

    @Override
    public void registerMissionListener(MissionListener listener, Set<EventTypeEnum> interestEvents) {
        if (CollectionUtils.isEmpty(interestEvents)) {
            return;
        }
        interestEvents.forEach(eventTypeEnum -> {
            Set<MissionListener> listeners = listenerMap.get(eventTypeEnum.getCode());
            if (listeners == null) {
                listeners = Sets.newConcurrentHashSet();
                listenerMap.putIfAbsent(eventTypeEnum.getCode(), listeners);
            }
            listeners.add(listener);
        });
    }

    @Override
    public void deregisterMissionListener(MissionListener listener) {
        Set<Byte> keys = Sets.newHashSet();
        listenerMap.entrySet().forEach(entry -> {
            if (entry.getValue().contains(listener))
                keys.add(entry.getKey());
        });
        for (Byte key : keys) {
            Set<MissionListener> listeners = listenerMap.get(key);
            if (listeners != null) {
                synchronized (listeners) {
                    listeners.remove(key);
                    if (listeners.isEmpty()) {
                        listenerMap.remove(key);
                    }
                }
            }
        }
    }

    @Override
    public void removeAllListeners() {
        listenerMap.clear();
    }
}
