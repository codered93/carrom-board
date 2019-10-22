package com.game.turns;

import com.game.coin.CoinType;
import com.game.coin.CoinsConsumed;

public class RedStrike extends Turn {

    public RedStrike(){
        super("Red strike",3);
        coinsConsumed.add(new CoinsConsumed(CoinType.Red.toString(),1));
    }

}
