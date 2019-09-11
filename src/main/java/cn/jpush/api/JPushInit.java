package cn.jpush.api;

import cn.jiguang.common.ClientConfig;

public abstract class JPushInit {

    /**
     * initialize settings
     *
     * @param appKey
     * @param masterSecret
     */
    public static JPushClient init(String appKey, String masterSecret) {
        JPushUtil.appKey = appKey;
        JPushUtil.masterSecret = masterSecret;
        ClientConfig instance = ClientConfig.getInstance();
        instance.setTimeToLive(86400);//保留一天
        JPushUtil.jpushClient = new JPushClient(masterSecret, appKey, null, instance);
//        JPushUtil.jpushClient = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());
        return JPushUtil.jpushClient;
    }
}
