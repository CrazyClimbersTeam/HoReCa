package com.crazyclimbersteam.horeca.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.activities.MainActivity;
import com.crazyclimbersteam.horeca.dialogs.ChoosePictureDialog;
import com.soundcloud.android.crop.Crop;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class RegistrationFragment extends Fragment {

    public static String TAG = RegistrationFragment.class.getSimpleName();

    public static final String REGISTRATION_PREFS = "REGISTRATION_PREFS";
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_EMAIL = "USER_EMAIL";

    @InjectView(R.id.user_avatar) ImageView mUserAvatar;
    @InjectView(R.id.user_email) EditText mUserEmail;
    @InjectView(R.id.user_name) EditText mUserName;
    @InjectView(R.id.confirm_btn) Button mConfirmbtn;

    ChoosePictureDialog mTakePictureDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTakePictureDialog = new ChoosePictureDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registration_layout, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @OnClick(R.id.confirm_btn)
    public void confirmData(Button confirmBtn){
        if (mUserEmail.getText().length() != 0 && mUserName.getText().length() != 0){
            SharedPreferences registrationPrefs = getActivity().getSharedPreferences(REGISTRATION_PREFS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = registrationPrefs.edit();
            editor.putString(USER_NAME, mUserName.getText().toString());
            editor.putString(USER_EMAIL, mUserEmail.getText().toString());
            editor.apply();

            startMainActivity();
        }
    }

    @OnClick(R.id.user_avatar)
    public void setUserAvatar(ImageView imageView){
        mTakePictureDialog.show(getFragmentManager(), null);
    }

    private void startMainActivity(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
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

    private void beginCrop(Uri source) {
        Uri outputUri = Uri.fromFile(new File(getActivity().getCacheDir(), "cropped"));
        new Crop(source).output(outputUri).asSquare().start(getActivity());
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == Activity.RESULT_OK) {
            mUserAvatar.setImageURI(Crop.getOutput(result));
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(getActivity(), Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
