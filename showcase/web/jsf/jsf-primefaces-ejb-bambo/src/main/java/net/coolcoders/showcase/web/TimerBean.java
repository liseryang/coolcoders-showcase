package net.coolcoders.showcase.web;

import javax.annotation.Resource;
import javax.ejb.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: andreas
 * Date: 17.09.2010
 * Time: 17:56:49
 * To change this templae use File | Settings | File Templates.
 */
@Singleton
public class TimerBean {

    @Resource
    TimerService timerService;

    public void startTimer() {
        System.err.println("Entered startTimer");
        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setPersistent(true);
        Timer intervalTimer = timerService.createIntervalTimer(new Date(), 3000, timerConfig);
        
    }

    @Timeout
    public void timeout(Timer timer) {
        System.err.println("timeout");

    }
}
