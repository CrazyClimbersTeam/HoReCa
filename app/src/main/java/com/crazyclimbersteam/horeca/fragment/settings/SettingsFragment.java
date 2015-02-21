package com.crazyclimbersteam.horeca.fragment.settings;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.fragment.base.BaseFragment;

/**
 * @author A.Dudka
 */
public class SettingsFragment extends BaseFragment {
    public static String TAG = getFragmentTag(SettingsFragment.class);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View settingsView = inflater.inflate(R.layout.settings_layout, container, false);
        // Categories spinner
        initCategorysSpinner(settingsView);
        // Tags spinner
        initTagSpinner(settingsView);
        // Notification count spinner
        initNotificationCounySpinner(settingsView);
        return settingsView;
    }

    private void initNotificationCounySpinner(View settingsView) {
        Spinner notificationCountSpinner = (Spinner) settingsView.findViewById(R.id.notification_count_spinner);
        ArrayAdapter<CharSequence> notificationCountAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.notifications_count, R.layout.spinner_item);
        notificationCountAdapter.setDropDownViewResource(R.layout.notification_count_spinner_count);
        notificationCountSpinner.setAdapter(notificationCountAdapter);
    }

    private void initTagSpinner(View settingsView) {
        Spinner tagSpinner = (Spinner) settingsView.findViewById(R.id.tag_spinner);
        String[] tagArr = getResources().getStringArray(R.array.tags);
        Item[] tags = new Item[tagArr.length];
        Item tagItem;
        for (int i = 0; i < tagArr.length; i++) {
            tagItem = new Item();
            tagItem.setName(tagArr[i]);
            tagItem.setChecked(false);
            tags[i] = tagItem;
        }
        SpinnerAdapter tagAdapter = new SpinnerAdapter(getActivity(), R.layout.spinner_item, tags);
        tagAdapter.setDropDownViewResource(R.layout.spinner_drop_down_item);
        tagSpinner.setAdapter(tagAdapter);
    }

    private void initCategorysSpinner(View settingsView) {
        Spinner categorySpinner = (Spinner) settingsView.findViewById(R.id.category_spinner);
        String[] categoryArr = getResources().getStringArray(R.array.categories);
        Item[] categories = new Item[categoryArr.length];
        Item categoryItem;
        for (int i = 0; i < categories.length; i++) {
            categoryItem = new Item();
            categoryItem.setName(categoryArr[i]);
            categoryItem.setChecked(false);
            categories[i] = categoryItem;
        }
        SpinnerAdapter categoryAdapter = new SpinnerAdapter(getActivity(), R.layout.spinner_item, categories);
        categorySpinner.setAdapter(categoryAdapter);
        categoryAdapter.setDropDownViewResource(R.layout.spinner_drop_down_item);
        categorySpinner.setAdapter(categoryAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false);
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

    private static class SpinnerAdapter extends ArrayAdapter<Item>{
        private Item[] items;
        private LayoutInflater mInflater;

        public SpinnerAdapter(Context context, int viewResourceId, Item[] items) {
            super(context, viewResourceId, items);
            this.items = items;
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount(){
            return items.length;
        }

        public Item getItem(int position){
            return items[position];
        }

        public long getItemId(int position){
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
}
