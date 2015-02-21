package com.crazyclimbersteam.horeca.activity.detail.dataprovider;

import com.crazyclimbersteam.horeca.activity.detail.fragment.feedback.FeedbackItemModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mirash
 */
public class DetailsDataObservable {
    //TODO
    private List<WeakReference<FeedbackUpdateListener>> mFeedbackUpdateObservers = new ArrayList<>();

    public void addFeedbackUpdateObserver(FeedbackUpdateListener observer) {
        mFeedbackUpdateObservers.add(new WeakReference<>(observer));
    }

    protected void notifyFedbackUpdateObservers(List<FeedbackItemModel> items) {
        for (WeakReference<FeedbackUpdateListener> observer : mFeedbackUpdateObservers) {
            if (observer != null && observer.get() != null) {
                observer.get().onFeedbackUpdate(items);
            }
        }
    }

    protected void clearObservers() {
        mFeedbackUpdateObservers.clear();
    }
}
