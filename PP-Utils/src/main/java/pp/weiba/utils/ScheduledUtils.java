package pp.weiba.utils;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 定时刷新任务工具类
 *
 * @author weiba
 * @date 2024/5/9 9:33
 */
@Log4j2
public class ScheduledUtils {

    private static final ScheduledThreadPoolExecutor scheduledExecutor = ThreadUtil.createScheduledExecutor(CpuUtils.availableProcessors());

    private static final Map<String, ScheduledFuture> scheduledFutureMap = new HashMap<>();

    /**
     * 添加定时任务, 支持随机执行
     *
     * @param scheduledRandom 定时任务
     * @return 定时任务id
     */
    public static String schedule(ScheduledRunnable scheduledRandom) {
        cancel(scheduledRandom.getScheduledId());
        // 第一次立马执行, 后面的随机执行
        log.info("{} 任务初始化！", scheduledRandom.getScheduledId());
        if (scheduledRandom.isFirstNotDelay()) {
            scheduledRandom.getCommand().run();
            log.info("{} 任务已执行！", scheduledRandom.getScheduledId());
        }
        ScheduledFuture schedule = scheduledExecutor.schedule(new Runnable() {
                    @Override
                    public void run() {
                        // 判断任务是否已经取消
                        if (isCancelled(scheduledRandom)) return;

                        scheduledRandom.getCommand().run();
                        log.info("{} 任务已执行 {} 次！", scheduledRandom.getScheduledId(), scheduledRandom.getExecuteCount());

                        scheduledRandom.setExecuteCount(scheduledRandom.getExecuteCount() + 1);

                        // 可能在执行逻辑中将自己取消了
                        if (isCancelled(scheduledRandom)) return;

                        // 执行完毕重新加入到定时任务
                        scheduledFutureMap.put(scheduledRandom.getScheduledId(), scheduledExecutor.schedule(this, scheduledRandom.getNextDelay(), scheduledRandom.getUnit()));
                    }
                }, scheduledRandom.getNextDelay(), scheduledRandom.getUnit());
        String scheduledId = scheduledRandom.getScheduledId();
        scheduledFutureMap.put(scheduledId, schedule);
        return scheduledId;
    }

    private static boolean isCancelled(ScheduledRunnable scheduledRandom) {
        ScheduledFuture scheduledFuture = scheduledFutureMap.get(scheduledRandom.getScheduledId());
        if (scheduledFuture == null || scheduledFuture.isCancelled()) {
            log.info("{} 任务已取消！", scheduledRandom.getScheduledId());
            scheduledFutureMap.remove(scheduledRandom.getScheduledId());
            return true;
        } else if (scheduledRandom.getMaxExecuteCount() != null && scheduledRandom.getExecuteCount() == scheduledRandom.getMaxExecuteCount()) {
            log.info("{} 任务已到大最大执行次数！", scheduledRandom.getScheduledId());
            return true;
        }
        return false;
    }

    public static String schedule(Runnable runnable, long delay, TimeUnit unit) {
        String scheduledId = RandomUtil.randomString(18);
        scheduledFutureMap.put(scheduledId, scheduledExecutor.schedule(()->{
            runnable.run();
            scheduledFutureMap.remove(scheduledId);
        }, delay, unit));
        return scheduledId;
    }

    public static boolean cancel(String scheduledId) {
        if (StrUtil.isBlank(scheduledId)) return false;
        ScheduledFuture scheduledFuture = scheduledFutureMap.remove(scheduledId);
        return scheduledFuture == null ? false : scheduledFuture.cancel(true);
    }

}
