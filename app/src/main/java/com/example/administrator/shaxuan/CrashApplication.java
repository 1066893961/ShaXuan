package com.example.administrator.shaxuan;

import android.app.Application;

import com.example.administrator.shaxuan.crash.CrashHandler;

/**
 * Created by Administrator on 2018/4/2.
 */

public class CrashApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}