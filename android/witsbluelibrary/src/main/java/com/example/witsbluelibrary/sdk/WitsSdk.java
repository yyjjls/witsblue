package com.example.witsbluelibrary.sdk;

import android.content.Context;

import com.example.witsbluelibrary.induce.Induce;

/**
 * sdk接口
 */
public interface WitsSdk {



    /**
     * 获得感应开锁对象
     * @return
     */
    Induce getInduceUnlock();


    /**
     * 获得开锁对象 还是没有实现
     */
}
