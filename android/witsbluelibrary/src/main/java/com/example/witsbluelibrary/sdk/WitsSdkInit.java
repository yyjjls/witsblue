package com.example.witsbluelibrary.sdk;

import android.content.Context;

import com.example.witsbluelibrary.induce.Induce;

/**
 * SDK初始化
 */
public final class WitsSdkInit implements Register, WitsSdk {


    private Context context;

    private static Register mRegister;

    private WitsSdkInit() {
    }

    public static Register getInstance() {
        if (mRegister == null) {
            synchronized (WitsSdkInit.class) {
                if (mRegister == null) {
                    mRegister = new WitsSdkInit();
                }
            }
        }
        return mRegister;
    }


    @Override
    public WitsSdk register(Context context, String appId, String userToken) {
        this.context = context;

        /**
         * 还需要实现具体的注册
         */


        return this;
    }


    @Override
    public Induce getInduceUnlock() {
        return Induce.instance(context);
    }
}
