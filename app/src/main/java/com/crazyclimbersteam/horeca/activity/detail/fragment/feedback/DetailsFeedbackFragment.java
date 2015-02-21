package com.crazyclimbersteam.horeca.activity.detail.fragment.feedback;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.activity.detail.dataprovider.FeedbackUpdateListener;
import com.crazyclimbersteam.horeca.activity.detail.fragment.DetailsTabFragment;

import java.util.List;

import static com.crazyclimbersteam.horeca.utils.LogUtils.log;

/**
 * @author Mirash
 */
public class DetailsFeedbackFragment extends DetailsTabFragment implements FeedbackUpdateListener {
    private ListView mListView;

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
    }

    private void initViews(View rootView) {
        View addFeedback = rootView.findViewById(R.id.details_tab_add_feedback);
        addFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddFeedbackDialog();
            }
        });
        mListView = (ListView) rootView.findViewById(R.id.details_tab_feedback_list);
        updateFeedbackDataAdapter(mDataProvider.getFeedbacks());
    }

    private void showAddFeedbackDialog() {
    }

    public void updateFeedbackDataAdapter(List<FeedbackItemModel> items) {
        mListView.setAdapter(new FeedbackAdapter(getActivity(), 0, items));
    }

    @Override
    public void onFeedbackUpdate(List<FeedbackItemModel> feedbackItemModelList) {
        log("onFeedbackUpdate ");
        updateFeedbackDataAdapter(feedbackItemModelList);
    }
}
