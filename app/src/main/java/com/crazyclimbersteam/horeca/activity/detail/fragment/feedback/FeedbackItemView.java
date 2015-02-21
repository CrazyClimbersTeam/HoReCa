package com.crazyclimbersteam.horeca.activity.detail.fragment.feedback;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.crazyclimbersteam.horeca.R;

/**
 * @author Mirash
 */
public class FeedbackItemView extends LinearLayout {
    private ImageView mIconView;
    private TextView mNameView;
    private TextView mCommentView;
    private RatingBar mRatingView;

    public FeedbackItemView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.details_feedback_item, this);
        mIconView = (ImageView) findViewById(R.id.details_feedback_item_icon);
        mNameView = (TextView) findViewById(R.id.details_feedback_item_name);
        mCommentView = (TextView) findViewById(R.id.details_feedback_item_comment);
        mRatingView = (RatingBar) findViewById(R.id.details_feedback_item_rating);
    }

    public void setIcon(Drawable drawable) {
        mIconView.setImageDrawable(drawable);
    }

    public void setName(String name) {
        mNameView.setText(name);
    }

    public void setComment(String comment) {
        mCommentView.setText(comment);
    }

    public void setRating(float ratingValue) {
        mRatingView.setRating(ratingValue);
    }
}
