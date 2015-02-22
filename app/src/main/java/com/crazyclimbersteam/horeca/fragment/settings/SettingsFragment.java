package com.crazyclimbersteam.horeca.fragment.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.dialog.ChoosePictureDialog;
import com.crazyclimbersteam.horeca.fragment.base.BaseFragment;
import com.crazyclimbersteam.horeca.fragment.welcome.RegistrationFragment;
import com.crazyclimbersteam.horeca.utils.LogUtils;
import com.jeapie.JeapieAPI;
import com.soundcloud.android.crop.Crop;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author A.Dudka
 */
public class SettingsFragment extends BaseFragment {
    public static String TAG = getFragmentTag(SettingsFragment.class);

    public static final String USER_NAME = "USER_NAME";
    public static final String USER_AVATAR_URI = "USER_AVATAR_URI";

    @InjectView(R.id.user_avatar)
    protected ImageView mUserAvatar;
    @InjectView(R.id.user_name)
    protected EditText mUserName;

    private SharedPreferences mRegistrationPrefs;
    private ChoosePictureDialog mTakePictureDialog;
    private JeapieAPI mJeapieAPI;
    private View mSettingsView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTakePictureDialog = new ChoosePictureDialog();
        mRegistrationPrefs = getActivity().getSharedPreferences(RegistrationFragment.REGISTRATION_PREFS, Context.MODE_PRIVATE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mSettingsView = inflater.inflate(R.layout.settings_layout, container, false);
        ButterKnife.inject(this, mSettingsView);
        mUserName.setText(mRegistrationPrefs.getString(RegistrationFragment.USER_NAME, ""));

        //FIXME: FIND BETTER APPROACH
        String avatarUri = mRegistrationPrefs.getString(RegistrationFragment.USER_AVATAR_URI, "");
        if (avatarUri.length() > 0) {
            mUserAvatar.setImageURI(Uri.parse(avatarUri));
        } else {
            mUserAvatar.setImageResource(R.drawable.avatar_icon);
        }
        return mSettingsView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mJeapieAPI = JeapieAPI.getInstance();
        mUserName.setOnEditorActionListener(new DoneOnEditorActionListener());
        // Categories spinner
        initCategorySpinner(mSettingsView);
        // Tags spinner
        initTagSpinner(mSettingsView);
        // Notification count spinner
        initNotificationCountSpinner(mSettingsView);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        super.onActivityResult(requestCode, resultCode, result);
        if (requestCode == Crop.REQUEST_PICK && resultCode == Activity.RESULT_OK) {
            beginCrop(result.getData());
        } else if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode, result);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    private void initCategorySpinner(View settingsView) {
        Spinner categorySpinner = (Spinner) settingsView.findViewById(R.id.category_spinner);
        String[] categoryArr = getResources().getStringArray(R.array.categories);
        final Item[] categories = new Item[categoryArr.length];
        Item categoryItem;
        for (int i = 0; i < categories.length; i++) {
            categoryItem = new Item();
            categoryItem.setName(categoryArr[i]);
            categoryItem.setChecked(true);
            categories[i] = categoryItem;
        }

        final SpinnerAdapter categoryAdapter = new SpinnerAdapter(getActivity(), R.layout.spinner_item, categories);
        categorySpinner.setAdapter(categoryAdapter);
        categoryAdapter.setDropDownViewResource(R.layout.spinner_drop_down_item);
        categorySpinner.setAdapter(categoryAdapter);

        //FIXME I don't confident that this shit is worked
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Item item = categoryAdapter.getItem(position);
   /*             if (item.isChecked()) {
                    mJeapieAPI.emitAddTagEvent(item.getName());
                } else {
                    mJeapieAPI.emitRemoveTagEvent(item.getName());
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //full throw
            }
        });

        new Thread() {
            @Override
            public void run() {
                for (Item item : categories) {
                    mJeapieAPI.emitAddTagEvent(item.getName());
                }
            }
        }.start();
    }

    private void initTagSpinner(View settingsView) {
        Spinner tagSpinner = (Spinner) settingsView.findViewById(R.id.tag_spinner);
        String[] tagArr = getResources().getStringArray(R.array.tags);
        final Item[] tags = new Item[tagArr.length];
        Item tagItem;
        for (int i = 0; i < tagArr.length; i++) {
            tagItem = new Item();
            tagItem.setName(tagArr[i]);
            tagItem.setChecked(true);
            tags[i] = tagItem;
        }

        new Thread() {
            @Override
            public void run() {
                for (Item item : tags) {
                    mJeapieAPI.emitAddTagEvent(item.getName());
                }
            }
        }.start();

        final SpinnerAdapter tagAdapter = new SpinnerAdapter(getActivity(), R.layout.spinner_item, tags);
        tagAdapter.setDropDownViewResource(R.layout.spinner_drop_down_item);
        tagSpinner.setAdapter(tagAdapter);

        //FIXME I don't confident that this shit is worked
        tagSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Item item = tagAdapter.getItem(position);
    /*            if (item.isChecked()) {
                    mJeapieAPI.emitAddTagEvent(item.getName());
                } else {
                    mJeapieAPI.emitRemoveTagEvent(item.getName());
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initNotificationCountSpinner(View settingsView) {
        Spinner notificationCountSpinner = (Spinner) settingsView.findViewById(R.id.notification_count_spinner);
        ArrayAdapter<CharSequence> notificationCountAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.notifications_count, R.layout.spinner_item);
        notificationCountAdapter.setDropDownViewResource(R.layout.notification_count_spinner_count);
        notificationCountSpinner.setAdapter(notificationCountAdapter);
    }


    @OnClick(R.id.user_avatar)
    public void setUserAvatar(de.hdodenhof.circleimageview.CircleImageView imageView) {
        mTakePictureDialog.show(getFragmentManager(), null);
    }


    private void beginCrop(Uri source) {
        Uri outputUri = Uri.fromFile(new File(getActivity().getCacheDir(), "cropped"));
        new Crop(source).output(outputUri).asSquare().start(getActivity());
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == Activity.RESULT_OK) {
            SharedPreferences.Editor editor = mRegistrationPrefs.edit();
            editor.putString(USER_AVATAR_URI, Crop.getOutput(result).toString());
            editor.apply();
            LogUtils.log("handleCrop");
            mUserAvatar.setImageURI(Crop.getOutput(result));
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(getActivity(), Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private static class Item {
        String name;
        boolean checked;

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static class SpinnerAdapter extends ArrayAdapter<Item> {
        private Item[] items;
        private LayoutInflater mInflater;

        public SpinnerAdapter(Context context, int viewResourceId, Item[] items) {
            super(context, viewResourceId, items);
            this.items = items;
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return items.length;
        }

        public Item getItem(int position) {
            return items[position];
        }

        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = mInflater.inflate(R.layout.spinner_item, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.spinner_item);
            textView.setText(items[position].getName());
            return convertView;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            convertView = mInflater.inflate(R.layout.spinner_drop_down_item, parent, false);
            TextView textView = (TextView) convertView.findViewById(R.id.spinner_drop_down_item);
            textView.setText(items[position].getName());
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.spinner_checkbox);
            checkBox.setChecked(items[position].isChecked());
            return convertView;
        }
    }

    private class DoneOnEditorActionListener implements TextView.OnEditorActionListener {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                SharedPreferences.Editor editor = mRegistrationPrefs.edit();
                editor.putString(USER_NAME, mUserName.getText().toString());
                editor.apply();
                return true;
            }
            return false;
        }
    }
}
