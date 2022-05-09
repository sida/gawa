package sida.yasuharu.myapplication.gawa.Util;


import android.os.Handler;

import static android.os.Looper.getMainLooper;

public class MyUtil {

    public interface delayInterface {
        void start();
    }

    static public void delay(int msec, delayInterface callBack) {
        Handler handler = new Handler(getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callBack.start();
            }
        }, msec);
    }
}
