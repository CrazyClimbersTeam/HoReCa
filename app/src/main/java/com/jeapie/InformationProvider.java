package com.jeapie;

interface InformationProvider {
    String getManufacturer();

    String getModel();

    String getAndroidVersion();

    int getDisplayWidth();

    int getDisplayHeight();

    String getUuid();

    String getLanguage();

    String getTimezone();
}
