package com.shivam.demo.utils;

import android.os.Bundle;

public interface IFragmentChangeListener {

    void pushFragment(int fragmentId, Bundle data);

    void popFragment();
}
