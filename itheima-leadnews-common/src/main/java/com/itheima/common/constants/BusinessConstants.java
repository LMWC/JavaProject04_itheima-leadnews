package com.itheima.common.constants;

public interface BusinessConstants {
    //实名认证相关
   public static class ApUserRealnameConstants{
        //创建中
        public static final Integer SHENHE_ING=0;
        //待审核
        public static final Integer SHENHE_WAITING=1;
        //审核失败
        public static final Integer SHENHE_FAIL=2;
        /**
         * 审核通过
         */
        public static final Integer SHENHE_SUCCESS=9;
    }


    //业务2
    public static class MqConstants {
        /**
         * 文章自动审核
         */
        public static final String WM_NEWS_AUTO_SCAN_TOPIC = "wm.news.auto.scan.topic";

    }

    //业务3
    public static class ScanConstants{
        /**
         * 通过
         */
        public static final String PASS = "pass";
        /**
         * 拒绝
         */
        public static final String BLOCK="block";
        /**
         * 不确定
         */
        public static final String REVIEW="review";

    }
}