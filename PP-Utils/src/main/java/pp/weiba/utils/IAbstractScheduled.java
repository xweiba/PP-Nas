package pp.weiba.utils;
import lombok.extern.log4j.Log4j2;

/**
* 定时任务定制流程
*
* @author weiba
* @date 2024/5/9 10:24
*/
public interface IAbstractScheduled extends IScheduled {

    void scheduledRun();

    String scheduledBusinessType();

    String scheduledBusinessId();

    default ScheduledRunnable initScheduled() {
        return ScheduledRunnable.builder()
                .businessId(this.scheduledBusinessId())
                .businessType(this.scheduledBusinessType())
                .command(()->this.scheduledRun())
                .build();
    }

    default String scheduledStart() {
        return this.start(this.initScheduled());
    }

    default boolean scheduledCancel(String scheduledId) {
        return this.cancel(scheduledId);
    }

    default ScheduledRunnable buildScheduled() {
        ScheduledRunnable scheduledRunnable = this.initScheduled();
        this.customConfig(scheduledRunnable);
        return scheduledRunnable;
    }

    default void customConfig(ScheduledRunnable scheduledRunnable) {

    }

}
