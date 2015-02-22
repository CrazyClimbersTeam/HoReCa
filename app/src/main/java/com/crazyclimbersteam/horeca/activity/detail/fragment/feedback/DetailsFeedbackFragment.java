package com.crazyclimbersteam.horeca.activity.detail.fragment.feedback;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.activity.detail.dataprovider.DetailsListUpdateListener;
import com.crazyclimbersteam.horeca.activity.detail.fragment.DetailsTabFragment;

import java.util.List;

import static com.crazyclimbersteam.horeca.utils.LogUtils.log;

/**
 * @author Mirash
 */
public class DetailsFeedbackFragment extends DetailsTabFragment implements DetailsListUpdateListener<FeedbackItemModel> {
    private ListView mListView;
    private RatingBar mAddFeedbackView;
    private List<FeedbackItemModel> mItems;

    private FeedbackItemModel mUserFeedback;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.details_tab_feedback, null);
        initViews(rootView);
        mDataProvider.addFeedbackUpdateObserver(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDataProvider.removeFeedbackUpdateObserver(this);
    }

    private void initViews(View rootView) {
        mAddFeedbackView = (RatingBar) rootView.findViewById(R.id.details_tab_add_feedback);
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
        mListView = (ListView) rootView.findViewById(R.id.details_tab_feedback_list);
        updateFeedbackDataAdapter(mDataProvider.getFeedbacks());
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
        mItems.add(0, mUserFeedback);
        ((ArrayAdapter) mListView.getAdapter()).notifyDataSetChanged();
        mAddFeedbackView.setVisibility(View.GONE);
    }

    public void updateFeedbackDataAdapter(List<FeedbackItemModel> items) {
        mItems = items;
        mListView.setAdapter(new FeedbackAdapter(getActivity(), 0, items));
    }

    @Override
    public void onDataUpdate(List<FeedbackItemModel> feedbackItemModelList) {
        log("onDataUpdate ");
        updateFeedbackDataAdapter(feedbackItemModelList);
    }
}
