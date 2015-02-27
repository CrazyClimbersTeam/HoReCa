package com.crazyclimbersteam.horeca.activity.detail.dataprovider;

import com.crazyclimbersteam.horeca.activity.detail.fragment.feedback.FeedbackItemModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static com.crazyclimbersteam.horeca.activity.detail.fragment.photo.DetailPhotoAdapter.Item;

/**
 * @author Mirash
 */
public class DetailsDataObservable {
    //TODO
    private List<WeakReference<DetailsListUpdateListener<FeedbackItemModel>>> mFeedbackUpdateObservers = new ArrayList<>();
    private List<WeakReference<DetailsListUpdateListener<Item>>> mPhotoUpdateObservers = new ArrayList<>();

    public void addFeedbackUpdateObserver(DetailsListUpdateListener<FeedbackItemModel> observer) {
        mFeedbackUpdateObservers.add(new WeakReference<>(observer));
    }

    public void addPhotoUpdateObserver(DetailsListUpdateListener<Item> observer) {
        mPhotoUpdateObservers.add(new WeakReference<>(observer));
    }

    public void removeFeedbackUpdateObserver(DetailsListUpdateListener<FeedbackItemModel> observer) {
        //TODO oops
        mFeedbackUpdateObservers.remove(observer);
    }

    public void removePhotoUpdateObserver(DetailsListUpdateListener<Item> observer) {
        //TODO oops
        mPhotoUpdateObservers.remove(observer);
    }

    protected void notifyFeedbackUpdateObservers(List<FeedbackItemModel> items) {
        for (WeakReference<DetailsListUpdateListener<FeedbackItemModel>> observer : mFeedbackUpdateObservers) {
            if (observer != null && observer.get() != null) {
                observer.get().onDataUpdate(items);
            }
        }
    }

    protected void notifyPhotoUpdateObservers(List<Item> items) {
        for (WeakReference<DetailsListUpdateListener<Item>> observer : mPhotoUpdateObservers) {
            if (observer != null && observer.get() != null) {
                observer.get().onDataUpdate(items);
            }
        }
    }

    protected void clearObservers() {
        mFeedbackUpdateObservers.clear();
    }
}
