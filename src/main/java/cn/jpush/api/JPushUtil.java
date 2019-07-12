package cn.jpush.api;

import cn.jpush.api.schedule.ScheduleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class JPushUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JPushUtil.class);
    //在极光注册上传应用的 appKey 和 masterSecret
    public static String appKey = null;////必填，例如466f7032ac604e02fb7bda89
    public static String masterSecret = null;//必填，每个应用都对应一个masterSecret
    public static JPushClient jpushClient;
    public static ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();//单线程定时任务
    public static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static ConcurrentHashMap<String, Future> concurrentHashMap = new ConcurrentHashMap<>();

    public static String addScheduled(String scheduleName, Date scheduleTime, Runnable runnable) {
        String format = JPushUtil.yyyyMMddHHmmss.format(scheduleTime);
        long startTime = (scheduleTime.getTime() - System.currentTimeMillis()) / 1000;
        LOGGER.info(scheduleName + "通知任务执行时间：" + format + "\t\t通知任务还剩" + startTime + "秒执行");
        concurrentHashMap.put(scheduleName, JPushUtil.scheduledExecutorService.schedule(runnable, startTime, TimeUnit.SECONDS));//添加到集合里面
        return scheduleName;
    }

    public static void cancelScheduled(String scheduleName) {
        if (concurrentHashMap.get(scheduleName) != null) {
            concurrentHashMap.get(scheduleName).cancel(true);
            LOGGER.info(scheduleName + "通知任务取消时间：" + yyyyMMddHHmmss.format(new Date()));
        } else {
            LOGGER.info(scheduleName + "通知任务不存在：" + yyyyMMddHHmmss.format(new Date()));
        }
    }
}
