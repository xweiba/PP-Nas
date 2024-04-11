package pp.weiba.service.download.client.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author xiaoweiba1028@gmail.com
 * @description Aria2请求类型
 * @date 2023/5/29 0:05
 */
public enum Aria2Method {

    AddUri("aria2.addUri"), // 新增任务
    ForcePause("aria2.forcePause"), // 暂停任务
    UnPause("aria2.unpause"), // 恢复暂停任务
    ForceRemove("aria2.forceRemove"), // 删除任务,进行中任务会进入已停止任务列表,暂停中任务会直接删除
    RemoveDownloadResult("aria2.removeDownloadResult"), // 删除已完成或已停止任务
    PurgeDownloadResult("aria2.purgeDownloadResult"), // 删除所有已完成任务
    TellActive("aria2.tellActive"), // 获取下载中任务
    TellWaiting("aria2.tellWaiting"), // 获取等待中任务
    TellStopped("aria2.tellStopped"), // 获取已完成/已停止任务
    TellStatus("aria2.tellStatus"), // 获取任务详细状态
    GetOption("aria2.getOption"), // 获取任务设置信息,
    ChangeOption("aria2.changeOption"), // 修改任务设置信息,
    GetGlobalOption("aria2.getGlobalOption"), // 获取全局设置信息
    ChangeGlobalOption("aria2.changeGlobalOption"), // 修改全局设置信息
    SystemMulticall("system.multicall"), // 将多个XML-RPC请求封装在一个单一的请求中，然后将其发送到XML-RPC服务器。服务器会按照请求的顺序执行这些请求，并将每个请求的结果按照同样的顺序返回给客户端。 任务重试时使用，先获取任务详情，再新增一个一样配置的任务。
    GetGlobalStat("aria2.getGlobalStat"); // 获取全局下载任务状态, 下载中个数, 暂停中个数

    private final String value;

    Aria2Method(String value) {
        this.value = value;
    }

    @JSONField
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
