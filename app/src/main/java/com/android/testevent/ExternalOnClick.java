package com.android.testevent;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 2019/7/11 20:58
 */
public class ExternalOnClick implements View.OnClickListener {
    TextView tv_tips;

    public ExternalOnClick(TextView tv_tips) {
        this.tv_tips = tv_tips;
    }

    @Override
    public void onClick(View v) {
        tv_tips.setText("点击了外部类的按钮");
    }
}
