package cn.jpush.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JPushUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JPushUtil.class);
    //在极光注册上传应用的 appKey 和 masterSecret
    public static String appKey = null;////必填，例如466f7032ac604e02fb7bda89
    public static String masterSecret = null;//必填，每个应用都对应一个masterSecret
    public static JPushClient jpushClient;
    public static ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();//单线程定时任务
    public static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void addScheduled(Date scheduleTime, Runnable runnable) {
        String format = JPushUtil.yyyyMMddHHmmss.format(scheduleTime);
        long startTime = (scheduleTime.getTime() - System.currentTimeMillis()) / 1000;
        LOGGER.info("通知任务执行时间：" + format);
//        System.out.println(currentTime + "-----" + startTime);
//        System.out.println(currentTime / 1000 + "-----" + startTime / 1000);
//        service.scheduleAtFixedRate(runnable, 10, 1, TimeUnit.SECONDS);
        LOGGER.info("通知任务还剩" + startTime + "秒执行");
        JPushUtil.scheduledExecutorService.schedule(runnable, startTime, TimeUnit.SECONDS);
    }
}
