package com.android.testevent;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SystemConfigActivity extends AppCompatActivity {

    @BindView(R.id.edit_ori)
    EditText editOri;
    @BindView(R.id.edit_navigation)
    EditText editNavigation;
    @BindView(R.id.edit_touch)
    EditText editTouch;
    @BindView(R.id.edit_mnc)
    EditText editMnc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_config);
        ButterKnife.bind(this);
        getSystemConfig();
    }

    @OnClick(R.id.btn_get)
    public void onViewClicked() {
        getSystemConfig();
    }

    public void getSystemConfig() {
        Configuration configuration = getResources().getConfiguration();
        editOri.setText(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE ? "横向屏幕" : "竖向屏幕");
        editNavigation.setText(configuration.orientation == Configuration.NAVIGATION_NONAV
                ? "没有方向控制" : configuration.orientation == Configuration.NAVIGATION_WHEEL
                ? "滚轮控制方向" : configuration.orientation == Configuration.NAVIGATION_DPAD
                ? "方向键控制方向" : "轨迹球控制方向");
        editTouch.setText(configuration.touchscreen == Configuration.TOUCHSCREEN_NOTOUCH ? "不支持触摸" : "支持触摸操作");
        editMnc.setText(String.valueOf(configuration.mnc));
    }
}
