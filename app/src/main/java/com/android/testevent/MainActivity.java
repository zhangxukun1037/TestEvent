package com.android.testevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_tips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tv_tips = findViewById(R.id.tv_tips);
        findViewById(R.id.btn_act).setOnClickListener(this);
        findViewById(R.id.tv_anonymous_inner_class).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_tips.setText("点击了匿名内部类的按钮");
            }
        });
        findViewById(R.id.tv_anonymous_class).setOnClickListener(new OnClick());
        findViewById(R.id.tv_external_classes).setOnClickListener(new ExternalOnClick(tv_tips));

        findViewById(R.id.btn_system_info).setOnClickListener(this);
        findViewById(R.id.btn_simulated_progress_bar).setOnClickListener(this);
    }

    public void bindClick(View view) {
        tv_tips.setText("点击了绑定到标签的按钮");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_act) {
            tv_tips.setText("点击了Activity作为监听器的按钮");
        } else if (v.getId() == R.id.btn_system_info) {
            startActivity(new Intent(this, SystemConfigActivity.class));
        } else if (v.getId() == R.id.btn_simulated_progress_bar) {
            startActivity(new Intent(this, SimulatedPBActivity.class));
        }
    }

    @butterknife.OnClick(R.id.btn_butter_knife)
    public void onViewClicked() {
        tv_tips.setText("点击了ButterKnife绑定的按钮");
    }

    private class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            tv_tips.setText("点击了内部类的按钮");
        }
    }
}
