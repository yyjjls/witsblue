package com.example.witsbluelibrary.induce;

import android.content.Context;

public interface InduceUnlock {




    //是否支持感应开锁
    boolean isReaction();

    //感应开锁是否已经运行
    boolean isOpenInduce();

    //开启感应开锁
    boolean openInduceUnlock();


}
