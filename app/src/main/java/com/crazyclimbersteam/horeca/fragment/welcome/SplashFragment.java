package com.crazyclimbersteam.horeca.fragment.welcome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.fragment.base.BaseFragment;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

public class SplashFragment extends BaseFragment {

    public static String TAG = getFragmentTag(SplashFragment.class);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.splash_fragment_layout, container, false);
        SVG svg = SVGParser.getSVGFromResource(getResources(), R.raw.horeca_svg);
        ImageView logo = (ImageView) view.findViewById(R.id.logo);
        logo.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        logo.setImageDrawable(svg.createPictureDrawable());
        return view;
    }
}
