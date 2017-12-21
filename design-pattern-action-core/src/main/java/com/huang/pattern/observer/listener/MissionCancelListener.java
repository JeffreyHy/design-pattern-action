package com.huang.pattern.observer.listener;

import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Mission cancel event listener
 * <p>
 * Created by JeffreyHy on 2017/12/21.
 */
@Component
public class MissionCancelListener implements MissionListener<MissionCancelEvent> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MissionEventPublisher missionEventPublisher;

    @Override
    public void handleEvent(MissionCancelEvent event) {
        logger.info("mission cancel event,missionId:{},timestamp:{}", event.getMissionContext().getMissionId(), event.getTimestamp());
    }

    @PostConstruct
    public void init() {
        missionEventPublisher.registerMissionListener(this, Sets.newHashSet(EventTypeEnum.CANCEL));
    }
}
