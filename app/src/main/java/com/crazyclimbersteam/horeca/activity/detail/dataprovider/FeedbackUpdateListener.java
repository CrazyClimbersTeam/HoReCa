package com.crazyclimbersteam.horeca.activity.detail.dataprovider;

import com.crazyclimbersteam.horeca.activity.detail.fragment.feedback.FeedbackItemModel;

import java.util.List;

/**
 * @author Mirash
 */
public interface FeedbackUpdateListener {
    void onFeedbackUpdate(List<FeedbackItemModel> feedbackItemModelList);
}
