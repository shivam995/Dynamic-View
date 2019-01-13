package com.sample.demo.custom;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class CustomET extends AppCompatEditText implements BaseView {
    public CustomET(Context context) {
        super(context);
    }

    public CustomET(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomET(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public String getValue() {
        return String.valueOf(getText());
    }
}
