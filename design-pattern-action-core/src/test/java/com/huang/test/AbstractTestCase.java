package com.huang.test;

import com.huang.context.Application;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by JeffreyHy on 2017/12/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@SpringBootConfiguration
public class AbstractTestCase extends TestCase{
}
