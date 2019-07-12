package cn.jpush.api.client;

import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.schedule.ScheduleResult;

import java.util.Collection;
import java.util.Date;

public abstract class JPushBase {

    private String alert;
    private String content;
    private Collection<String> aliases;
    private Boolean apns;

    private String id;
    private Integer type;
    private String url;

    private PushPayload pushPayload;


    public JPushBase(String alert, String content, Collection<String> aliases, Boolean apns) {
        this.alert = alert;
        this.content = content;
        this.aliases = aliases;
        this.apns = apns;
    }

    public JPushBase(String alert, String content, Collection<String> aliases, Boolean apns, String id, Integer type, String url) {
        this.alert = alert;
        this.content = content;
        this.aliases = aliases;
        this.apns = apns;
        this.id = id;
        this.type = type;
        this.url = url;
    }

    public abstract PushResult push() throws Exception;

    /**
     * @param scheduleName 任务名称
     * @param scheduleTime 任务时间
     * @param runnable     实现回调
     * @return
     * @throws Exception
     */
    public abstract String push(String scheduleName, Date scheduleTime, Runnable runnable) throws Exception;

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Collection<String> getAliases() {
        return aliases;
    }

    public void setAliases(Collection<String> aliases) {
        this.aliases = aliases;
    }

    public Boolean getApns() {
        return apns;
    }

    public void setApns(Boolean apns) {
        this.apns = apns;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PushPayload getPushPayload() {
        return pushPayload;
    }

    public void setPushPayload(PushPayload pushPayload) {
        this.pushPayload = pushPayload;
    }
}
