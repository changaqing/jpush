package cn.jpush.api.client;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushUtil;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.schedule.ScheduleResult;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JPushIos extends JPushBase {

    public JPushIos(String alert, String content, Collection<String> aliases, Boolean apns) {
        super(alert, content, aliases, apns);
        //构建推送对象：
        // setPlatform 平台是 iOS，
        // setAudience 推送目标是 aliases，
        // setNotification 推送内容同时包括通知与消息 - 通知信息是 ALERT，角标数字为 5，通知声音为 "happy"，并且附加字段 from = "JPush"；消息内容是 MSG_CONTENT。
        // 通知是 APNs 推送通道的，消息是 JPush 应用内消息通道的。APNs 的推送环境是“生产”（如果不显式设置的话，Library 会默认指定为开发）
        super.setPushPayload(PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(getAliases() == null ? Audience.all() : Audience.alias(getAliases()))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(getAlert()) //弹出的内容
                                .setSound("happy") //声音
//                                .setBadge(5)  //角标数字
                                .build())
                        .build())
                .setMessage(Message.content(getContent()))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(getApns())
                        .build())
                .build());
    }

    public JPushIos(String alert, String content, Collection<String> aliases, Boolean apns, String id, Integer type, String url) {
        super(alert, content, aliases, apns, id, type, url);
        super.setPushPayload(PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(aliases == null ? Audience.all() : Audience.alias(aliases))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(alert) //弹出的内容
                                .setSound("happy") //声音
//                                .setBadge(5)  //角标数字
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
    public PushResult push() throws APIConnectionException, APIRequestException {
        return JPushUtil.jpushClient.sendPush(super.getPushPayload());
    }

    @Override
    public ScheduleResult push(String scheduleName, Date scheduleTime, Runnable runnable) throws Exception {
        JPushUtil.addScheduled(scheduleTime, runnable);
        return JPushUtil.jpushClient.createSingleSchedule(scheduleName, JPushUtil.yyyyMMddHHmmss.format(scheduleTime), super.getPushPayload());
    }
}
