package com.game.turns;

import com.game.coin.CoinType;
import com.game.coin.CoinsConsumed;

public class Strike extends Turn {

    public Strike(){
        super("Strike",1);
        coinsConsumed.add(new CoinsConsumed(CoinType.Black.toString(),1));
    }

}
