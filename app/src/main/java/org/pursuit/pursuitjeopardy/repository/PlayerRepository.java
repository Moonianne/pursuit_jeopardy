package org.pursuit.pursuitjeopardy.repository;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import org.pursuit.pursuitjeopardy.db.PlayerDBHelper;
import org.pursuit.pursuitjeopardy.model.Player;

public class PlayerRepository {

    private static PlayerRepository singleInstance;
    private final PlayerDBHelper playerDBHelper;
    private static Player player;

    private PlayerRepository(Application application){
        playerDBHelper = new PlayerDBHelper(application);
    }

    public static PlayerRepository getSingleInstance(@NonNull Application application){
        if(singleInstance != null){
            return singleInstance;
        }else{
            singleInstance = new PlayerRepository(application);
            return singleInstance;
        }
    }

    public Player getPlayer() {
        if(player == null){
            player = new Player();return player;
        }else{
            return player;
        }
    }

    public void addPlayerToDatabase(Player player){
        playerDBHelper.addPlayer(player);
    }
}
