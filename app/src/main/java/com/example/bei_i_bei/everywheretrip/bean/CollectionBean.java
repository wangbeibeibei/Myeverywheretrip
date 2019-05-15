package com.example.bei_i_bei.everywheretrip.bean;

import java.util.List;

public class CollectionBean {


    /**
     * code : 0
     * desc : 处理成功
     * result : {"page":1,"limit":20,"count":4,"collectedRoutes":[{"id":196,"cityID":52,"title":"濑户内海艺术双岛","intro":"直岛·丰岛美术馆巡礼","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1521428871050_dc075f14d81fa0a814913d743bfbadf1.jpg","isPurchased":false,"createdAt":"2019-05-12","price":"1.90"},{"id":202,"cityID":37,"title":"雍和宫〜北新桥","intro":"爱上历史深处的文艺小巷","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1522286621202_7b91111fb2dffe11b646759be235f7e7.jpg","isPurchased":false,"createdAt":"2019-05-10","price":"1.90"},{"id":12,"cityID":15,"title":"镰仓","intro":"8小时海街小城治愈之旅","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1510559916208_a791f18de091581aa0ac437887686f52.jpg","isPurchased":false,"createdAt":"2019-05-10","price":"1.90"},{"id":81,"cityID":1,"title":"吉祥寺","intro":"7小时东京人气街区NO.1","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1510728189818_93cb364a522e6d7ccbcdf534ccea66ab.jpg","isPurchased":false,"createdAt":"2019-05-10","price":"1.90"}]}
     */

    private int code;
    private String desc;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * page : 1
         * limit : 20
         * count : 4
         * collectedRoutes : [{"id":196,"cityID":52,"title":"濑户内海艺术双岛","intro":"直岛·丰岛美术馆巡礼","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1521428871050_dc075f14d81fa0a814913d743bfbadf1.jpg","isPurchased":false,"createdAt":"2019-05-12","price":"1.90"},{"id":202,"cityID":37,"title":"雍和宫〜北新桥","intro":"爱上历史深处的文艺小巷","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1522286621202_7b91111fb2dffe11b646759be235f7e7.jpg","isPurchased":false,"createdAt":"2019-05-10","price":"1.90"},{"id":12,"cityID":15,"title":"镰仓","intro":"8小时海街小城治愈之旅","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1510559916208_a791f18de091581aa0ac437887686f52.jpg","isPurchased":false,"createdAt":"2019-05-10","price":"1.90"},{"id":81,"cityID":1,"title":"吉祥寺","intro":"7小时东京人气街区NO.1","priceInCents":190,"cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1510728189818_93cb364a522e6d7ccbcdf534ccea66ab.jpg","isPurchased":false,"createdAt":"2019-05-10","price":"1.90"}]
         */

        private int page;
        private int limit;
        private int count;
        private List<CollectedRoutesBean> collectedRoutes;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<CollectedRoutesBean> getCollectedRoutes() {
            return collectedRoutes;
        }

        public void setCollectedRoutes(List<CollectedRoutesBean> collectedRoutes) {
            this.collectedRoutes = collectedRoutes;
        }

        public static class CollectedRoutesBean {
            /**
             * id : 196
             * cityID : 52
             * title : 濑户内海艺术双岛
             * intro : 直岛·丰岛美术馆巡礼
             * priceInCents : 190
             * cardURL : http://cdn.banmi.com/banmiapp/rahdna/1521428871050_dc075f14d81fa0a814913d743bfbadf1.jpg
             * isPurchased : false
             * createdAt : 2019-05-12
             * price : 1.90
             */

            private int id;
            private int cityID;
            private String title;
            private String intro;
            private int priceInCents;
            private String cardURL;
            private boolean isPurchased;
            private String createdAt;
            private String price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCityID() {
                return cityID;
            }

            public void setCityID(int cityID) {
                this.cityID = cityID;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public int getPriceInCents() {
                return priceInCents;
            }

            public void setPriceInCents(int priceInCents) {
                this.priceInCents = priceInCents;
            }

            public String getCardURL() {
                return cardURL;
            }

            public void setCardURL(String cardURL) {
                this.cardURL = cardURL;
            }

            public boolean isIsPurchased() {
                return isPurchased;
            }

            public void setIsPurchased(boolean isPurchased) {
                this.isPurchased = isPurchased;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }
}
