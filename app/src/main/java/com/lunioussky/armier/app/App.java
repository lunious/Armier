package com.lunioussky.armier.app;

import android.app.Application;

import com.lzy.okgo.OkGo;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by 11645 on 2018/3/13.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        okgo();
    }

    private void okgo() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //全局的读取超时时间
        builder.readTimeout(10000, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(10000, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(10000, TimeUnit.MILLISECONDS);

        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())
                .setRetryCount(3);
    }
}
