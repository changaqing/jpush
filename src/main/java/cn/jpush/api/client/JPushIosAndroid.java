package cn.jpush.api.client;

import cn.jpush.api.JPushUtil;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.schedule.ScheduleResult;

import java.util.Collection;
import java.util.Date;

public class JPushIosAndroid extends JPushBase {

    public JPushIosAndroid(String alert, String content, Collection<String> aliases, Boolean apns) {
        super(alert, content, aliases, apns);
        super.setPushPayload(PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(aliases == null ? Audience.all() : Audience.alias(aliases))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(alert)
                                .setSound("happy")
                                .build())
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(alert)
                                .build())
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(content)
                        .build())//消息内容 需要前端获取的 alert是可以看见的  content是看不见的
                .setOptions(Options.newBuilder()
                        .setApnsProduction(apns)//默认为false 当为true时不用开发环境了
                        .build())
                .build());
    }

    public JPushIosAndroid(String alert, String content, Collection<String> aliases, Boolean apns, String id, Integer type, String url) {
        super(alert, content, aliases, apns, id, type, url);
        super.setPushPayload(PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(aliases == null ? Audience.all() : Audience.alias(aliases))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(alert)
                                .setSound("happy")
                                .addExtra("id", id)
                                .addExtra("type", type)
                                .addExtra("url", url)
                                .build())
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(alert)
                                .addExtra("id", id)
                                .addExtra("type", type)
                                .addExtra("url", url)
                                .build())
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(content)
                        .addExtra("id", id)
                        .addExtra("type", type)
                        .addExtra("url", url)
                        .build())//消息内容 需要前端获取的 alert是可以看见的  content是看不见的
                .setOptions(Options.newBuilder()
                        .setApnsProduction(apns)//默认为false 当为true时不用开发环境了
                        .build())
                .build());
    }

    @Override
    public PushResult push() throws Exception {
        return JPushUtil.jpushClient.sendPush(super.getPushPayload());
    }

    @Override
    public String push(String scheduleName, Date scheduleTime, Runnable runnable){
        //先不用极光定时推送 因为他的进行删除任务的时候需要任务id 而id是他自己定义的 如果使用他的id进行删除 那么我重启项目就没办法找到这个任务的id 除非把id记录到数据库 但是这样挺费劲的所以 只提供添加任务 在回调里面自己执行相应的业务
//        ScheduleResult singleSchedule = JPushUtil.jpushClient.createSingleSchedule(scheduleName, JPushUtil.yyyyMMddHHmmss.format(scheduleTime), super.getPushPayload());
        return JPushUtil.addScheduled(scheduleName, scheduleTime, runnable);
    }


}
