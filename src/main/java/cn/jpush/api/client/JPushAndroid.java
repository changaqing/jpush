package cn.jpush.api.client;

import cn.jpush.api.JPushUtil;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.schedule.ScheduleResult;

import java.util.Collection;
import java.util.Date;

public class JPushAndroid extends JPushBase {

    public JPushAndroid(String alert, String content, Collection<String> aliases, Boolean apns) {
        super(alert, content, aliases, apns);
        super.setPushPayload(PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(aliases == null ? Audience.all() : Audience.alias(aliases))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(alert)
                                .build())
                        .build())
                .setMessage(Message.content(content))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(apns)
                        .build())
                .build());
    }

    public JPushAndroid(String alert, String content, Collection<String> aliases, Boolean apns, String id, Integer type, String url) {
        super(alert, content, aliases, apns, id, type, url);
        super.setPushPayload(PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(aliases == null ? Audience.all() : Audience.alias(aliases))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(alert)
                                .addExtra("id", id)
                                .addExtra("type", type)
                                .addExtra("url", url)
                                .build())
                        .build())
                .setMessage(Message.content(content))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(apns)
                        .build())
                .build());
    }

    @Override
    public PushResult push() throws Exception {
        return JPushUtil.jpushClient.sendPush(super.getPushPayload());
    }

    @Override
    public ScheduleResult push(String scheduleName, Date scheduleTime, Runnable runnable) throws Exception {
        JPushUtil.addScheduled(scheduleTime, runnable);
        return JPushUtil.jpushClient.createSingleSchedule(scheduleName, JPushUtil.yyyyMMddHHmmss.format(scheduleTime), super.getPushPayload());
    }
}
