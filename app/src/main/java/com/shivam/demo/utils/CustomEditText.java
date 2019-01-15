package com.shivam.demo.utils;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

public class CustomEditText extends AppCompatEditText implements BaseView {
    private IValueObserver valueObserver;
    public CustomEditText(Context context,IValueObserver valueObserver) {
        super(context);
        this.valueObserver = valueObserver;

        init();
    }

    private void init() {

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    valueObserver.onValueChange(String.valueOf(s));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    public String getValue() {
        return String.valueOf(getText());
    }




}
