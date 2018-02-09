package com.huang.test.pattern.observer.listener;

import com.huang.pattern.observer.listener.MissionCancelEvent;
import com.huang.pattern.observer.listener.MissionContext;
import com.huang.pattern.observer.listener.MissionEventPublisher;
import com.huang.pattern.observer.listener.MissionPublishEvent;
import com.huang.test.AbstractTestCase;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by JeffreyHy on 2017/12/20.
 */
public class MissionListenerTest extends AbstractTestCase {
    @Resource
    private MissionEventPublisher genericMissionEventPublisher;

    @Test
    public void testMissionPublishEvent() {
        MissionContext context = new MissionContext();
        context.setMissionId(1111);
        genericMissionEventPublisher.publishEvent(new MissionPublishEvent(context));
    }

    @Test
    public void testMissionCancelEvent() {
        MissionContext context = new MissionContext();
        context.setMissionId(1111);
        genericMissionEventPublisher.publishEvent(new MissionCancelEvent(context));
    }

    @Test
    public void testMissionEvent() {
        MissionContext context = new MissionContext();
        context.setMissionId(1111);
        genericMissionEventPublisher.publishEvent(new MissionPublishEvent(context));
        genericMissionEventPublisher.publishEvent(new MissionCancelEvent(context));
    }
}
