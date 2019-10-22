package com.game.coin;

public class Coin {
    private String name;
    private int count;

    public Coin(String name,int count) {
        this.name = name;
        this.count=count;
    }

    public String getName() {
        return name;
    }


    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
