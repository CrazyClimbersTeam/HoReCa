package com.crazyclimbersteam.horeca.activity.detail.dataprovider;

import android.os.AsyncTask;

import com.crazyclimbersteam.horeca.activity.detail.fragment.feedback.FeedbackItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mirash
 */
public class DetailsDataProvider extends DetailsDataObservable {
    private AsyncTask mRetrieveFeedbacksTask;
    private AsyncTask mRetrieveImageTask;
    private AsyncTask mRetrieveInfoTask;
    private AsyncTask mRetrievePhotosTask;

    private List<FeedbackItemModel> mFeedbackItems = new ArrayList<>();

    public void updateAllData() {
        updateFeedbacks();
        updateImage();
        updateInfo();
        updatePhotos();
    }

    //TODO
    public void updateFeedbacks() {
        if (mRetrieveFeedbacksTask == null) {
            mRetrieveFeedbacksTask = new AsyncTask<Void, Void, List<FeedbackItemModel>>() {
                @Override
                protected List<FeedbackItemModel> doInBackground(Void[] params) {
                    //TODO
                    List<FeedbackItemModel> itemModels = creteRandomFeedbackItemModel();
                    return itemModels;
                }

                @Override
                protected void onPostExecute(List<FeedbackItemModel> items) {
                    mRetrieveFeedbacksTask = null;
                    if (items != null) {
                        mFeedbackItems = items;
                        notifyFedbackUpdateObservers(mFeedbackItems);
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    public void updateImage() {
    }

    public void updateInfo() {

    }

    public void updatePhotos() {

    }

    public List<FeedbackItemModel> getFeedbacks() {
        return mFeedbackItems;
    }

    //random data generator
    private List<FeedbackItemModel> creteRandomFeedbackItemModel() {
        List<FeedbackItemModel> itemModels = new ArrayList<>();
        itemModels.add(new FeedbackItemModel("nice one!", "Mirash", 5));
        itemModels.add(new FeedbackItemModel("what!", "m0r0", 3));
        itemModels.add(new FeedbackItemModel("de!", "dRon", 2));
        itemModels.add(new FeedbackItemModel("it's so awesome!", "Den", 5));
        itemModels.add(new FeedbackItemModel("i will never go there again", "Ololo", 1));
        return itemModels;
    }

    public void clear() {
        clearObservers();
        clearTask(mRetrieveFeedbacksTask);
        clearTask(mRetrieveImageTask);
        clearTask(mRetrieveInfoTask);
        clearTask(mRetrievePhotosTask);
    }

    private void clearTask(AsyncTask task) {
        if (task != null) {
            task.cancel(true);
        }
    }


}
