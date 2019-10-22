package com.game.turns;

import com.game.coin.CoinType;
import com.game.coin.CoinsConsumed;

public class DefunctCoin extends Turn {

    public DefunctCoin(){
        super("Defunct coin",-2);
        coinsConsumed.add(new CoinsConsumed(CoinType.Black.toString(),1));
    }

}
