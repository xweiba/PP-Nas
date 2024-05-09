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

    public static String random(ScheduledRandomRunnable scheduledRandom) {
        // 第一次立马执行, 后面的随机执行
        if (scheduledRandom.isFirstNotDelay()) scheduledRandom.getCommand().run();
        ScheduledFuture schedule = scheduledExecutor.schedule(new Runnable() {
                    @Override
                    public void run() {
                        scheduledRandom.getCommand().run();
                        // 判断任务是否已经取消
                        ScheduledFuture scheduledFuture = scheduledFutureMap.get(scheduledRandom.getScheduledId());
                        if (scheduledFuture == null || scheduledFuture.isCancelled()) {
                            scheduledFutureMap.remove(scheduledRandom.getScheduledId());
                            log.info("任务已取消！");
                            return;
                        }
                        // 执行完毕重新加入到定时任务
                        scheduledFutureMap.put(scheduledRandom.getScheduledId(), scheduledExecutor.schedule(this, scheduledRandom.getNextDelay(), scheduledRandom.getUnit()));
                    }
                }, scheduledRandom.getNextDelay(), scheduledRandom.getUnit());
        String scheduledId = scheduledRandom.getScheduledId();
        scheduledFutureMap.put(scheduledId, schedule);
        return scheduledId;
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
