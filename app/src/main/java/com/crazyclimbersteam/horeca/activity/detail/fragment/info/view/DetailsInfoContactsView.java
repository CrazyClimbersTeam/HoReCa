package com.crazyclimbersteam.horeca.activity.detail.fragment.info.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crazyclimbersteam.horeca.R;

/**
 * @author Mirash
 */
public class DetailsInfoContactsView extends LinearLayout {
    private TextView mPhoneTextView;
    private TextView mAddressTextView;

    public DetailsInfoContactsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        inflate(context, R.layout.details_tab_info_contacts_view, this);
        mPhoneTextView = (TextView) findViewById(R.id.detail_info_contacts_phone_value);
        mAddressTextView = (TextView) findViewById(R.id.detail_info_contacts_address_value);
    }

    public void setPhone(String phone) {
        mPhoneTextView.setText(phone);
    }

    public void setAddress(String address) {
        mAddressTextView.setText(address);
    }


}
