package com.game.turns;

import com.game.coin.CoinType;
import com.game.coin.CoinsConsumed;

import java.util.ArrayList;
import java.util.List;

public class TurnsFactory {
    public Turn getTurn(String turnName){
        List<CoinsConsumed> coinsConsumed=new ArrayList<>();
        if(turnName.equals("Multistrike")){
            coinsConsumed.add(new CoinsConsumed(CoinType.Black.toString(),2));
            return new Turn("Multistrike",2,coinsConsumed);
        }
        else if(turnName.equals("Defunct coin"))
            return new Turn("Defunct coin",-2,coinsConsumed);
        else if(turnName.equals("Red strike")){
            coinsConsumed.add(new CoinsConsumed(CoinType.Red.toString(),1));
            return new Turn("Red strike",3,coinsConsumed);
        }
        else if(turnName.equals("Strike")){
            coinsConsumed.add(new CoinsConsumed(CoinType.Black.toString(),1));
            return new Turn("Strike",1,coinsConsumed);
        }
        else if(turnName.equals("Striker strike"))
            return new Turn("Striker strike",-1,coinsConsumed);
        else if(turnName.equals("None"))
            return new Turn("None",0,coinsConsumed);
        return null;
    }
}
