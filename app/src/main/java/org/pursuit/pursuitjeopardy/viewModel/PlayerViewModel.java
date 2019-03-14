package org.pursuit.pursuitjeopardy.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import org.pursuit.pursuitjeopardy.model.Player;
import org.pursuit.pursuitjeopardy.repository.PlayerRepository;
import org.pursuit.pursuitjeopardy.repository.QuestionsRepository;

public final class PlayerViewModel extends AndroidViewModel {

    private final PlayerRepository playerRepository;
    private final Player player;
    private MutableLiveData<Integer> playerPoints;

    public PlayerViewModel(@NonNull Application application) {
        super(application);
        playerRepository = PlayerRepository.getSingleInstance(application);
        player = playerRepository.getPlayer();
        playerPoints = new MutableLiveData<>();
    }


    public void updateToPlayerScore(int points){
        player.setCurrentScore(player.getCurrentScore() + points);
        playerPoints.setValue(player.getCurrentScore());
    }

    public void setPlayer(String name){
        player.setName(name);
        player.setCurrentScore(0);
    }

    public void addPlayerToDatabase(){
        playerRepository.addPlayerToDatabase(player);
    }

    public String getPlayerName(){
        return player.getName();
    }

    public LiveData<Integer> getPlayerPoints() {
        return playerPoints;
    }


}
