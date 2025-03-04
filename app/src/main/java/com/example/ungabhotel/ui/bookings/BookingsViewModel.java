package com.example.ungabhotel.ui.bookings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BookingsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BookingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is bookings fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}