package pp.weiba.utils;

import cn.hutool.core.util.RandomUtil;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 定时任务信息
 *
 * @author weiba
 * @date 2024/5/9 9:31
 */
@Accessors(chain = true)
@Data
@SuperBuilder
public class ScheduledRunnable {

    @Builder.Default
    private String scheduledId;

    private String businessType;

    private String businessId;

    private Runnable command;

    // 首次是否立即执行
    @Builder.Default
    private boolean firstNotDelay = Boolean.TRUE;

    // 延时执行时间
    @Builder.Default
    private long delay = 30l;

    // 首次执行时的延时时间， 在服务重启后又不想立马执行时使用, 只使用一次
    private Long initNextDelay;

    // 已执行次数
    @Builder.Default
    private Integer executeCount = 0;

    /* 最大执行次数 */
    private Integer maxExecuteCount;

    @Builder.Default
    private TimeUnit unit = TimeUnit.MINUTES;

    // 开启随机执行
    @Builder.Default
    private boolean isRandom = Boolean.TRUE;

    @Builder.Default
    private long maxRandom = 10;

    @Builder.Default
    private long minRandom = 0;

    public long getNextDelay() {
        if (!this.isRandom || this.initNextDelay != null) {
            if (initNextDelay != null) {
                Long initNextDelayTemp = this.initNextDelay;
                this.initNextDelay = null;
                return initNextDelayTemp;
            }
            return this.getDelay();
        }
        return this.getDelay() + RandomUtil.randomLong(this.getMinRandom(), this.getMaxRandom());
    }

    public String getScheduledId() {
        if (this.scheduledId != null) {
            return this.scheduledId;
        }
        this.scheduledId = this.getBusinessType()  + "_" + this.getBusinessId();
        return this.scheduledId;
    }

    public void setScheduledId(String scheduledId) {
        throw new IllegalArgumentException("scheduledId 不可设置");
    }
}
