package com.pixelworld.utils;

import com.pixelworld.domain.Monster;
import com.pixelworld.domain.User;
import org.springframework.stereotype.Service;

/**
 * Created by BladeInShine on 15/12/22.
 */
@Service
public class BattleUtils {

    public String battleCalculation(User user, Monster monster){

        int uhp = user.getHp();
        int mhp = monster.getHp();
        boolean won = false;
        while(true){
            mhp -= (user.getAtk()-monster.getDef());
            if(mhp <= 0) {
                won = true;
                break;
            }
            uhp -= (monster.getAtk()-user.getDef());
            if(uhp <= 0) {
                won = false;
                break;
            }
        }
        return won ? monster.getDrop() : "failed";

    }

}
