package com.crazyclimbersteam.horeca.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazyclimbersteam.horeca.R;
import com.soundcloud.android.crop.Crop;

public class ChoosePictureDialog extends DialogFragment implements View.OnClickListener {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Choose image:");
        View view = inflater.inflate(R.layout.take_picture_dialog, null);
        view.findViewById(R.id.choose_from_gallery).setOnClickListener(this);
        view.findViewById(R.id.take_photo).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choose_from_gallery:
                Crop.pickImage(getActivity());
                dismiss();
                break;
            case R.id.take_photo:
                // TODO implement possibility to take photo from device camera
                dismiss();
                break;
        }
    }
}
