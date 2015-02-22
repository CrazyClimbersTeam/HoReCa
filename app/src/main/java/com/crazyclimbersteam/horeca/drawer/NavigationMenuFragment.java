package com.crazyclimbersteam.horeca.drawer;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.drawer.views.MenuItemsContainer;
import com.crazyclimbersteam.horeca.fragment.welcome.RegistrationFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * @author Mirash
 */
//TODO implement me
public class NavigationMenuFragment extends Fragment {
    private MenuItemsContainer mMenuItemContainer;

    @InjectView(R.id.user_avatar)
    ImageView mUserAvatar;
    @InjectView(R.id.user_name)
    TextView mUserName;
    @InjectView(R.id.user_email)
    TextView mUserEmail;

    SharedPreferences mRegistrationPrefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRegistrationPrefs = getActivity().getSharedPreferences(RegistrationFragment.REGISTRATION_PREFS, Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.drawer_layout, null);
        ButterKnife.inject(this, rootView);
        mUserName.setText(mRegistrationPrefs.getString(RegistrationFragment.USER_NAME, ""));
        mUserEmail.setText(mRegistrationPrefs.getString(RegistrationFragment.USER_EMAIL, ""));

        //FIXME: FIND BETTER APPROACH
        String avatarUri = mRegistrationPrefs.getString(RegistrationFragment.USER_AVATAR_URI, "");
        if (avatarUri.length() > 0){
            mUserAvatar.setImageURI(Uri.parse(avatarUri));
        }else {
            mUserAvatar.setImageResource(R.drawable.avatar_icon);
        }

        mMenuItemContainer = (MenuItemsContainer) rootView.findViewById(R.id.menu_item_container);
        return rootView;
    }

    public void setItemClickListener(MenuItemClickListener itemClickListener) {
        mMenuItemContainer.setMenuItemClickListener(itemClickListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
