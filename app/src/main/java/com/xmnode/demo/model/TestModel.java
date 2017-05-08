package com.xmnode.demo.model;

import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */
public class TestModel {

    /**
     * state : success
     * lastIconPlate : [{"title":"首页","notClick":"http://ecpic.rrslj.com/es/image/20160927_11500999.png","number":1,"clickUrl":"http://ecpic.rrslj.com/es/image/20160927_11493712.png"},{"title":"食鲜商城","notClick":"http://ecpic.rrslj.com/es/image/20160927_11503632.png","number":2,"clickUrl":"http://ecpic.rrslj.com/es/image/20160927_11502481.png"},{"title":"生活服务","notClick":"http://ecpic.rrslj.com/es/image/20160927_12263653.png","number":3,"clickUrl":"http://ecpic.rrslj.com/es/image/20160927_12262386.png"},{"title":"购物车","notClick":"http://ecpic.rrslj.com/es/image/20160927_12270632.png","number":4,"clickUrl":"http://ecpic.rrslj.com/es/image/20160927_1226502.png"},{"title":"我的","notClick":"http://ecpic.rrslj.com/es/image/20160927_12272919.png","number":5,"clickUrl":"http://ecpic.rrslj.com/es/image/20160927_12271878.png"}]
     * theMostNewTime : 2016-09-27 22:34:08
     */

    private String state;
    private String theMostNewTime;
    /**
     * title : 首页
     * notClick : http://ecpic.rrslj.com/es/image/20160927_11500999.png
     * number : 1
     * clickUrl : http://ecpic.rrslj.com/es/image/20160927_11493712.png
     */

    private List<LastIconPlateBean> lastIconPlate;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTheMostNewTime() {
        return theMostNewTime;
    }

    public void setTheMostNewTime(String theMostNewTime) {
        this.theMostNewTime = theMostNewTime;
    }

    public List<LastIconPlateBean> getLastIconPlate() {
        return lastIconPlate;
    }

    public void setLastIconPlate(List<LastIconPlateBean> lastIconPlate) {
        this.lastIconPlate = lastIconPlate;
    }

    public static class LastIconPlateBean {
        private String title;
        private String notClick;
        private int number;
        private String clickUrl;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNotClick() {
            return notClick;
        }

        public void setNotClick(String notClick) {
            this.notClick = notClick;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getClickUrl() {
            return clickUrl;
        }

        public void setClickUrl(String clickUrl) {
            this.clickUrl = clickUrl;
        }
    }
}
