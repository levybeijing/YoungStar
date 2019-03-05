package com.chuanqing.youngstar.tomp3;

import android.os.Environment;

import java.io.File;

class FileUtils {

    public static final String AUDIOPATH = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"star/audio/";
    public static final String PCMPATH = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"star/audio/pcm/";


    public static String getPcmFileAbsolutePath(String name){
        File file=new File(PCMPATH);
        if (!file.exists()){
            file.mkdirs();
        }
        return PCMPATH+name+".pcm";
    }


    public static String getWavFileAbsolutePath(String name){
        File file=new File(AUDIOPATH);
        if (!file.exists()){
            file.mkdirs();
        }
        return AUDIOPATH+name+".wav";
    }

}
