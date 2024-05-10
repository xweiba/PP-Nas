package pp.weiba.utils;

import java.util.concurrent.TimeUnit;

/**
* 定时刷新
*
* @author weiba
* @date 2024/5/9 10:18
*/
public interface IScheduled {
    default String start(ScheduledRunnable scheduledRandom) {
        return ScheduledUtils.schedule(scheduledRandom);
    }

    default boolean cancel(String scheduledId) {
        return ScheduledUtils.cancel(scheduledId);
    }

}
