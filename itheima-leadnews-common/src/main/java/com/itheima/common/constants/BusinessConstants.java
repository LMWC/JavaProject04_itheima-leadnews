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
        /**
         * 行为关注TOPIC
         */
        public static final String FOLLOW_BEHAVIOR_TOPIC="follow.behavior.topic";

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
    public static class ArticleConstants{
        public static final Short LOADTYPE_LOAD_MORE = 1;
        public static final Short LOADTYPE_LOAD_NEW = 2;
        /**
         * 默认频道
         */
        public static final String DEFAULT_TAG = "0";

        /**
         * 点赞
         */
        public static final Integer HOT_ARTICLE_LIKE_WEIGHT = 3;

        /**
         * 评论
         */
        public static final Integer HOT_ARTICLE_COMMENT_WEIGHT = 5;

        /**
         * 收藏
         */
        public static final Integer HOT_ARTICLE_COLLECTION_WEIGHT = 8;


        /**
         * 热点文章的前缀
         */
        public static final String HOT_ARTICLE_FIRST_PAGE = "hot_article_first_page_";

    }
}