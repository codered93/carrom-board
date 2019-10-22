package com.game.turns;

import com.game.coin.CoinType;
import com.game.coin.CoinsConsumed;

public class MultiStrike extends Turn {

    public MultiStrike(){
        super("Multistrike",2);
        coinsConsumed.add(new CoinsConsumed(CoinType.Black.toString(),2));
    }

}
