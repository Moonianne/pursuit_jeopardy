package org.pursuit.pursuitjeopardy.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import org.pursuit.pursuitjeopardy.model.Player;
import org.pursuit.pursuitjeopardy.repository.QuestionsRepository;

public final class PlayerViewModel extends ViewModel {

    private final QuestionsRepository questionsRepository;
    private final Player player;
    private MutableLiveData<Integer> playerPoints;

    public PlayerViewModel() {
        questionsRepository = QuestionsRepository.getRepositorySingleInstance();
        player = questionsRepository.setPlayer();
        playerPoints = new MutableLiveData<>();

    }

    public void updateToPlayerScore(int points){
        player.setCurrentScore(player.getCurrentScore() + points);
        playerPoints.setValue(player.getCurrentScore());
    }

    public LiveData<Integer> getPlayerPoints() {
        return playerPoints;
    }
}
