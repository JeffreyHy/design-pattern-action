package com.huang.pattern.observer.listener;

import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by JeffreyHy on 2017/12/20.
 */
@Component
public class MissionPublishListener implements MissionListener<MissionPublishEvent> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MissionEventPublisher missionEventPublisher;

    @Override
    public void handleEvent(MissionPublishEvent event) {
        logger.info("mission publish,missionId:{}", event.getMissionContext().getMissionId());
    }

    @PostConstruct
    public void init() {
        missionEventPublisher.registerMissionListener(this, Sets.newHashSet(EventTypeEnum.RELEASE));
    }
}
