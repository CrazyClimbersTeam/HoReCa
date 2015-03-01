package com.crazyclimbersteam.horeca.activity.detail.fragment.feedback;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import com.alexmirash.parallaxheaderviewpager.fragment.TabHolderListFragment;
import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.activity.detail.IDetailDataProvider;
import com.crazyclimbersteam.horeca.activity.detail.dataprovider.DetailsDataProvider;
import com.crazyclimbersteam.horeca.activity.detail.dataprovider.DetailsListUpdateListener;

import java.util.List;

import static com.crazyclimbersteam.horeca.utils.LogUtils.log;

/**
 * @author Mirash
 */
public class DetailsFeedbackFragment extends TabHolderListFragment<ListView> implements IDetailDataProvider, DetailsListUpdateListener<FeedbackItemModel> {
    private RatingBar mAddFeedbackView;

    private List<FeedbackItemModel> mItems;
    private FeedbackItemModel mUserFeedback;
    private FeedbackAdapter mAdapter;

    @Override
    protected ListView createListView(LayoutInflater inflater) {
        return new ListView(getActivity());
    }

    @Override
    protected void onCreateViewContent(LayoutInflater inflater, Bundle savedInstanceState) {
        getDataProvider().addFeedbackUpdateObserver(this);
        mAddFeedbackView = (RatingBar) inflater.inflate(R.layout.details_tab_feedback_rating_bar, null);
        mAddFeedbackView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mAddFeedbackView.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (mUserFeedback == null) {
                    mUserFeedback = new FeedbackItemModel();
                    mUserFeedback.setName("MyName");
                    mUserFeedback.setIcon(getResources().getDrawable(R.drawable.map_icon));
                }
                mUserFeedback.setRating(rating);
                showAddFeedbackDialog();
            }
        });
        updateFeedbackDataAdapter(getDataProvider().getFeedbacks());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getDataProvider().removeFeedbackUpdateObserver(this);
    }


    private void showAddFeedbackDialog() {
        final EditText editText = new EditText(getActivity());
        new AlertDialog.Builder(getActivity())
                .setTitle("Add feedback")
                .setMessage("")
                .setView(editText)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Editable value = editText.getText();
                        mUserFeedback.setMessage(value.toString());
                        log("onClick " + value);
                        saveUserFeedback();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                log("onCLick  cancel");
                // Do nothing.
            }
        }).show();
    }

    private void saveUserFeedback() {
        if (!mItems.isEmpty() && mItems.get(0) == null) {
            mItems.set(0, mUserFeedback);
        } else {
            mItems.add(0, mUserFeedback);
        }
        mAdapter.notifyDataSetChanged();
        mAddFeedbackView.setVisibility(View.GONE);
    }

    public void updateFeedbackDataAdapter(List<FeedbackItemModel> items) {
        mItems = items;
        mAdapter = new FeedbackAdapter(getActivity(), mAddFeedbackView, items);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onDataUpdate(List<FeedbackItemModel> feedbackItemModelList) {
        log("onDataUpdate ");
        updateFeedbackDataAdapter(feedbackItemModelList);
    }

    @Override
    public DetailsDataProvider getDataProvider() {
        Activity activity = getActivity();
        return ((IDetailDataProvider) activity).getDataProvider();
    }
}
