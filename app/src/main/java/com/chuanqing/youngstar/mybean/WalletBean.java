package com.chuanqing.youngstar.mybean;

public class WalletBean {

    /**
     * data : {"standard_coin":10000000,"bud_coin":10000000,"star_coin":9999937}
     * message : 请求成功
     * state : 1
     */

    private DataBean data;
    private String message;
    private int state;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public static class DataBean {
        /**
         * standard_coin : 10000000
         * bud_coin : 10000000
         * star_coin : 9999937
         */

        private int standard_coin;
        private int bud_coin;
        private int star_coin;

        public int getStandard_coin() {
            return standard_coin;
        }

        public void setStandard_coin(int standard_coin) {
            this.standard_coin = standard_coin;
        }

        public int getBud_coin() {
            return bud_coin;
        }

        public void setBud_coin(int bud_coin) {
            this.bud_coin = bud_coin;
        }

        public int getStar_coin() {
            return star_coin;
        }

        public void setStar_coin(int star_coin) {
            this.star_coin = star_coin;
        }
    }
}
