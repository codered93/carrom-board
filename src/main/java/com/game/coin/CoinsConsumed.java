package com.game.coin;

public class CoinsConsumed {
    private String coin;
    private int quantity;

    public CoinsConsumed(String coin, int quantity){
        this.coin=coin;
        this.quantity=quantity;
    }

    public String getCoin() {
        return coin;
    }

    public int getQuantity() {
        return quantity;
    }
}
