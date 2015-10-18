package com.crazyclimbersteam.horeca.activity.detail.dataprovider;

import java.util.List;

/**
 * @author Mirash
 */
public interface DetailsListUpdateListener<T> {
    void onDataUpdate(List<T> feedbackItemModelList);
}
