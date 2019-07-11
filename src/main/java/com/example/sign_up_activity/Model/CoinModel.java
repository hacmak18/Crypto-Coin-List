package com.example.sign_up_activity.Model;

public class CoinModel {



        public String id;
        public String name;
        public String symbol;
        public String price_usd ;
        public String percent_change_1h ;
        public String percent_change_24h;
        public String percent_change_7d ;

    public CoinModel() {

    }

    public CoinModel(String id, String name, String symbol, String price_usd, String percent_change_1h, String percent_change_24h, String percent_change_7d) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.price_usd = price_usd;
        this.percent_change_1h = percent_change_1h;
        this.percent_change_24h = percent_change_24h;
        this.percent_change_7d = percent_change_7d;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getPrice_usd() {
        return price_usd;
    }

    public String getPercent_change_1h() {
        return percent_change_1h;
    }

    public String getPercent_change_24h() {
        return percent_change_24h;
    }

    public String getPercent_change_7d() {
        return percent_change_7d;
    }
}

