package org.pursuit.pursuitjeopardy.viewModel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import org.pursuit.pursuitjeopardy.model.QuestionsModel;
import org.pursuit.pursuitjeopardy.repository.QuestionsRepository;

import java.util.List;

public final class QuestionsViewModel extends ViewModel {

    private QuestionsRepository questionsRepository;
    private LiveData<List<List<QuestionsModel>>> listLiveData;

    public QuestionsViewModel() {
        questionsRepository = QuestionsRepository.getRepositorySingleInstance();
        listLiveData = questionsRepository.getLiveData();

    }

    public LiveData<List<List<QuestionsModel>>> getListLiveData() {
        return listLiveData;
    }

}