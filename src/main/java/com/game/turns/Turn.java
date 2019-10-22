package com.game.turns;

import com.game.coin.CoinsConsumed;

import java.util.ArrayList;
import java.util.List;

public abstract class Turn {
    private String name;
     private int points;
     protected List<CoinsConsumed> coinsConsumed;

    public Turn(String name, int points){
        this.name=name;
        this.points=points;
        this.coinsConsumed=new ArrayList<>();
    }

    public int getPoints() {
        return points;
    }

    public List<CoinsConsumed> getCoinsConsumed() {
        return coinsConsumed;
    }

    public String getName() {
        return name;
    }
}
