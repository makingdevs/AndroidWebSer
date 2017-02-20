package com.makingdevs.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle
import android.widget.Button
import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView
import com.github.lzyzsd.circleprogress.CircleProgress;
import com.makingdevs.modulusuno.R
import is.arontibo.library.ElasticDownloadView;

public class ProgressBar extends AppCompatActivity {


    AnimatedCircleLoadingView animatedCircleLoadingView
    Button btn

     void startLoading() {
        animatedCircleLoadingView.startDeterminate();
    }

    void startPercentMockThread() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    for (int i = 0; i <= 100; i++) {
                        Thread.sleep(65);
                        changePercent(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
    }
    private void changePercent(final int percent) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                animatedCircleLoadingView.setPercent(percent);
            }
        });
    }
    void resetLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                animatedCircleLoadingView.resetLoading();
            }
        });
    }
    void escucha() {
        btn = (Button) findViewById(R.id.button)
        btn.onClickListener = {
            println "Hola"
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        animatedCircleLoadingView = (AnimatedCircleLoadingView) findViewById(R.id.circle_loading_view)
        btn = (Button) findViewById(R.id.button)
        startLoading()
        startPercentMockThread()






    }
}
