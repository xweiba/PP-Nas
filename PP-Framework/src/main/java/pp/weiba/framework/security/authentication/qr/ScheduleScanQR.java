package pp.weiba.framework.security.authentication.qr;
import cn.hutool.core.util.ClassUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.KeyValue;
import pp.weiba.framework.security.authentication.qr.model.ScanQRStatus;
import pp.weiba.framework.security.authentication.qr.model.ScheduleScanQRTaskData;
import pp.weiba.framework.utils.UserInfoUtils;
import pp.weiba.utils.ScheduledRunnable;
import pp.weiba.utils.ScheduledUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
* 扫码抽象模板
*
* @author weiba
* @date 2024/5/28 13:22
*/
@Log4j2
public class ScheduleScanQR<T> implements IScanQR<T> {

    private IScanQR<T> scanQRImpl;

    private Map<String, ScheduleScanQRTaskData<T>> scheduleScanQrTaskDataMap = new ConcurrentHashMap<>();

    public ScheduleScanQR(IScanQR<T> scanQRImpl) {
        this.scanQRImpl = scanQRImpl;
    }

    @Override
    public String getScanQrUrl(String key) {
        String scanQrUrl = scanQRImpl.getScanQrUrl(key);
        // 开启定时检测
        scheduledStart(key);
        return scanQrUrl;
    }

    @Override
    public ScanQRStatus checkScanQrStatus(String key) {
        // 这个只返回定时任务中的状态，执行完全依赖定时任务，防止qps限制
        return getScheduleTaskData(key).getScanQRStatus();
    }

    @Override
    public T scanQrComplate(String key) {
        // 这个只返回定时任务中的状态，执行完全依赖定时任务，防止qps限制
        // 登录成功才有数据
        T scanQrComplate = null;
        if (isScheduledTaskIsExecuted(key)) {
            ScheduleScanQRTaskData<T> scheduleTaskData = getScheduleTaskData(key);
            scanQrComplate = scheduleTaskData.getScanQrComplate();
            // 任务取消并取走就移除
            scheduleScanQrTaskDataMap.remove(key);
            ScheduledUtils.cancel(scheduleTaskData.getScheduleId());
        }
        return scanQrComplate;
    }

    protected void scheduledStart(String key) {
        String scheduleId = ScheduledUtils.schedule(buildScheduledRunnable(key));
        scheduleScanQrTaskDataMap.put(key, new ScheduleScanQRTaskData<T>(scheduleId));
    }

    protected ScheduledRunnable buildScheduledRunnable(String key) {
        return ScheduledRunnable.builder()
                .businessType("ScheduleScanQR-" + ClassUtil.getClassName(scanQRImpl, false))
                .businessId(key)
                .command(()->{ scheduleTaskRun(key);})
                .firstNotDelay(false)
                .delay(2)
                .unit(TimeUnit.SECONDS)
                .isRandom(false)
                .build();
    }

    protected void scheduleTaskRun(String key) {
        ScheduleScanQRTaskData<T> scheduleScanQRTaskData = scheduleScanQrTaskDataMap.get(key);
        if (scheduleScanQRTaskData == null) return;

        UserInfoUtils.setCurrentThreadUserInfo(new KeyValue("user", key));
        // 更新状态
        ScanQRStatus scanQRStatus = scanQRImpl.checkScanQrStatus(key);
        scheduleScanQRTaskData.setScanQRStatus(scanQRStatus);

        if (isScheduledTaskIsExecuted(key)) {
            if (scanQRStatus.getType() == ScanQRStatusType.CONFIRMED) {
                // 使用实际的实现
                T scanQrComplateResult = scanQRImpl.scanQrComplate(key);
                scheduleScanQRTaskData.setScanQrComplate(scanQrComplateResult);
            }
            ScheduledUtils.cancel(scheduleScanQRTaskData.getScheduleId());
        }
    }

    /**
     * 任务是否执行结束
     *
     * @param key
     * @return
     * @author weiba
     * @date 2024/5/29 13:40
     */
    protected boolean isScheduledTaskIsExecuted(String key) {
        ScanQRStatus scanQRStatus = getScheduleTaskData(key).getScanQRStatus();

        return scanQRStatus.getType() == ScanQRStatusType.CONFIRMED
                || scanQRStatus.getType() == ScanQRStatusType.CANCELED
                || scanQRStatus.getType() == ScanQRStatusType.EXPIRED;
    }

    private ScheduleScanQRTaskData<T> getScheduleTaskData(String key) {
        ScheduleScanQRTaskData<T> tScheduleScanQRTaskData = scheduleScanQrTaskDataMap.get(key);
        if (tScheduleScanQRTaskData == null) {
            throw new RuntimeException("扫描任务不存在，请重新生成扫码后获取");
        }
        return tScheduleScanQRTaskData;
    }
}
