package cn.jpush.api;

import cn.jpush.api.client.JPushIosAndroid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Arrays;

public class Demo extends JPushInit {

    private static final Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) throws ParseException {

        //初始化设置
        init("f83ab76be6cd5b80afe871a3", "72bf9ac7f9dd432da8879daf");

        //不带附加参数  alert：标题    content：内容  aliases：别名  apns：默认为false true是生产环境
        JPushIosAndroid jPushIosAndroid =
                new JPushIosAndroid("测试一下", "哈哈哈哈哈1", Arrays.asList("2008"), false);


        //带附加参数  id:内容id   type：消息类型   url：跳转链接
        JPushIosAndroid jPushIosAndroid2 =
                new JPushIosAndroid("定时任务测试一下", "哈哈哈哈哈1", Arrays.asList("2008"), false, "oid", 1, "http://www.baidu.com");

        try {
            //基础通知测试
            LOGGER.info(jPushIosAndroid.push().toString());
            //定时通知测试执行时间必须大于现在时间
            LOGGER.info(jPushIosAndroid2.push("定时任务", JPushUtil.yyyyMMddHHmmss.parse("2019-07-04 15:36:10")
                    , () -> System.out.println("定时任务通知")).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //@TODO 添加自定义任务测试
//        JPushUtil.addScheduled(JPushUtil.yyyyMMddHHmmss.parse("2019-07-04 15:28:00"), new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Hello !!");
//            }
//        });
    }


}
