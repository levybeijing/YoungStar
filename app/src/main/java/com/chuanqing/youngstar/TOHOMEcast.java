package com.chuanqing.youngstar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TOHOMEcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action){
            case Urls.TOHOMECAST:

                break;
        }
    }
}
