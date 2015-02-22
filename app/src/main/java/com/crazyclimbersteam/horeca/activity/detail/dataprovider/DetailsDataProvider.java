package com.crazyclimbersteam.horeca.activity.detail.dataprovider;

import android.content.res.Resources;
import android.os.AsyncTask;

import com.crazyclimbersteam.horeca.HorecApplication;
import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.activity.detail.fragment.feedback.FeedbackItemModel;
import com.crazyclimbersteam.horeca.net.pojo.DetailItemModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.crazyclimbersteam.horeca.activity.detail.fragment.photo.DetailPhotoAdapter.Item;

/**
 * @author Mirash
 */
public class DetailsDataProvider extends DetailsDataObservable {
    private AsyncTask mRetrieveFeedbacksTask;
    private AsyncTask mRetrieveImageTask;
    private AsyncTask mRetrieveInfoTask;
    private AsyncTask mRetrievePhotosTask;

    private List<FeedbackItemModel> mFeedbackItems = new ArrayList<>();
    private List<Item> mPhotoItems = new ArrayList<>();

    private DetailItemModel mItemModel;

    public void setDetailItem(DetailItemModel itemModel) {
        mItemModel = itemModel;
    }

    public DetailItemModel getDetailItem() {
        return mItemModel;
    }

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
                    mFeedbackItems = items != null ? items : new ArrayList<FeedbackItemModel>();
                    notifyFeedbackUpdateObservers(mFeedbackItems);
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    public void updateImage() {
    }

    public void updateInfo() {

    }

    public void updatePhotos() {
        if (mRetrievePhotosTask == null) {
            mRetrievePhotosTask = new AsyncTask<Void, Void, List<Item>>() {
                @Override
                protected List<Item> doInBackground(Void[] params) {
                    //TODO
                    try {
                        Thread.sleep(new Random().nextInt(5000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    List<Item> photoItems = creteRandomPhotoItems();
                    return photoItems;
                }

                @Override
                protected void onPostExecute(List<Item> items) {
                    mRetrievePhotosTask = null;
                    mPhotoItems = items != null ? items : new ArrayList<Item>();
                    notifyPhotoUpdateObservers(mPhotoItems);
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    public List<FeedbackItemModel> getFeedbacks() {
        return mFeedbackItems;
    }

    public List<Item> getPhotos() {
        return mPhotoItems;
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

    //random data generator
    private List<Item> creteRandomPhotoItems() {
        List<Item> items = new ArrayList<>();
        Resources res = HorecApplication.getInstance().getResources();
        items.add(new Item(res.getDrawable(R.drawable.ferret)));
        items.add(new Item(res.getDrawable(R.drawable.cartoon_ferret)));
        items.add(new Item(res.getDrawable(R.drawable.map_icon)));
        items.add(new Item(res.getDrawable(R.drawable.test_image_space)));
        items.add(new Item(res.getDrawable(R.drawable.ic_plusone_tall_off_client)));
        items.add(new Item(res.getDrawable(R.drawable.abc_btn_check_to_on_mtrl_015)));
        return items;
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
