package org.pursuit.pursuitjeopardy.repository;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import org.pursuit.pursuitjeopardy.db.PlayerDBHelper;
import org.pursuit.pursuitjeopardy.model.Player;

public class PlayerRepository {
    private final PlayerDBHelper playerDBHelper;

    private static PlayerRepository singleInstance;
    private static Player player;

    public Player getPlayer() {
        if (player == null) {
            player = new Player();
        }
        return player;
    }

    public static PlayerRepository getSingleInstance(@NonNull Application application) {
        if (singleInstance == null) {
            singleInstance = new PlayerRepository(application);
        }
        return singleInstance;
    }

    private PlayerRepository(Application application) {
        playerDBHelper = new PlayerDBHelper(application);
    }

    public void addPlayerToDatabase(Player player) {
        playerDBHelper.addPlayer(player);
    }
}