package cn.jpush.api;

import cn.jpush.api.client.JPushIosAndroid;
import cn.jpush.api.schedule.ScheduleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

public class Demo extends JPushInit {

    private static final Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) throws ParseException {

        //初始化设置
        init("f83ab76be6cd5b80afe871a3", "72bf9ac7f9dd432da8879daf");

        //不带附加参数  alert：标题    content：内容  aliases：别名  apns：默认为false true是生产环境
        final JPushIosAndroid jPushIosAndroid =
                new JPushIosAndroid("测试一下", "哈哈哈哈哈1", Arrays.asList("2008","aca089a18d7712e3d69cd872a519cf5ff691d3956018a617bb0b4c8be6238f31"), false);


        //带附加参数  id:内容id   type：消息类型   url：跳转链接
//        JPushIosAndroid jPushIosAndroid2 =
//                new JPushIosAndroid("定时任务测试一下", "哈哈哈哈哈1", Arrays.asList("2008"), false, "oid", 1, "http://www.baidu.com");

        try {
            //基础通知测试
            System.out.println(jPushIosAndroid.push().toString());
//            LOGGER.info(jPushIosAndroid.push().toString()); aca089a18d7712e3d69cd872a519cf5ff691d3956018a617bb0b4c8be6238f31


            //定时通知测试执行时间必须大于现在时间
//            Date date = new Date();
//            date.setSeconds(date.getSeconds() + 10);
//            LOGGER.info(jPushIosAndroid2.push("定时任务1", date
//                    , new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                jPushIosAndroid.push();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            System.out.println("定时任务通知1");
//                        }
//                    }).toString());
//
//
//            date.setSeconds(date.getSeconds() + 10);
//            String pushKey = jPushIosAndroid2.push("定时任务2", date
//                    , new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                jPushIosAndroid.push();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            System.out.println("定时任务通知2");
//                        }
//                    });
//            //取消任务
//            JPushUtil.cancelScheduled(pushKey);
//
//
//            date.setSeconds(date.getSeconds() + 10);
//            LOGGER.info(jPushIosAndroid2.push("定时任务3", date
//                    , new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                jPushIosAndroid.push();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            System.out.println("定时任务通知3");
//                        }
//                    }).toString());
//
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 添加自定义任务测试
//        JPushUtil.addScheduled(JPushUtil.yyyyMMddHHmmss.parse("2019-07-04 15:28:00"), new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Hello !!");
//            }
//        });
    }


}
