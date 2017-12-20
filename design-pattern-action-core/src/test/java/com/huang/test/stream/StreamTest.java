package com.huang.test.stream;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * Created by JeffreyHy on 2017/12/20.
 */
public class StreamTest extends TestCase {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Test
    public void testStream() {

        Map<String, Set<Integer>> map = Maps.newHashMap();
        map.put("key1", Sets.newHashSet(1, 2, 3));
        map.put("key2", Sets.newHashSet(2, 3, 5));
        map.put("key3", Sets.newHashSet(5, 7));
        Set<String> keys = Sets.newHashSet();
        map.entrySet().forEach(entry -> {
            if(entry.getValue().contains(3))
                keys.add(entry.getKey());
        });
        keys.forEach(key ->{
            logger.info(key);
        });

    }
}
