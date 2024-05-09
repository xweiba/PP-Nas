package pp.weiba.utils;

import cn.hutool.core.util.RandomUtil;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 随机定时任务信息
 *
 * @author xiaoweiba1028@gmail.com
 * @date 2024/5/9 9:31
 */
@Accessors(chain = true)
@Data
@SuperBuilder
public class ScheduledRandomRunnable {

    @Builder.Default
    private String scheduledId = RandomUtil.randomString(18);

    private Runnable command;

    // 首次是否立即执行
    @Builder.Default
    private boolean firstNotDelay = Boolean.TRUE;

    private long delay;

    private TimeUnit unit;

    private long maxRandom;

    private long minRandom;

    public long getNextDelay() {
        return this.getDelay() + RandomUtil.randomLong(this.getMinRandom(), this.getMaxRandom());
    }

}
