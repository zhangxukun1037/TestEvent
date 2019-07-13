package com.android.testevent;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SimulatedPBActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private Button btnGo;
    private int curProgress = 0;
    private int MAX_PROGRESS = 100;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                progressDialog.incrementProgressBy(1);
                curProgress++;
            }
        }
    };

    @Override
    protected void onDestroy() {
        //防止内存泄露 在Activity销毁时 清除Handler的回调和消息，并且置null
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulated_pb);

        btnGo = findViewById(R.id.btn_go);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog();
            }
        });

        Button btn_handler = findViewById(R.id.btn_handler);
        btn_handler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialogHandler();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (curProgress < MAX_PROGRESS) {
                            SystemClock.sleep(50);
                            mHandler.sendEmptyMessage(0);
                        }

                        if (curProgress >= MAX_PROGRESS) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.setMessage("进度走完了！");
                                    progressDialog.dismiss();
                                    Toast.makeText(SimulatedPBActivity.this, "进度走完了！", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }

    private void showProgressDialogHandler() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("任务完成百分比");
        progressDialog.setMessage("耗时任务的完成百分比");
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setMax(MAX_PROGRESS);
        progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.dismiss();
            }
        });
        curProgress = 0;
        progressDialog.show();
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("任务完成百分比");
        progressDialog.setMessage("耗时任务的完成百分比");
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setMax(MAX_PROGRESS);
        progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.dismiss();
            }
        });
        curProgress = 0;
        progressDialog.show();

        startProgress();
    }

    private void startProgress() {
        if (curProgress >= MAX_PROGRESS) {
            progressDialog.setMessage("进度走完了！");
            return;
        }
        if (!progressDialog.isShowing()) {
            return;
        }
        progressDialog.incrementProgressBy(1);
        curProgress++;
        btnGo.postDelayed(new Runnable() {
            @Override
            public void run() {
                startProgress();
            }
        }, 50);
    }
}
