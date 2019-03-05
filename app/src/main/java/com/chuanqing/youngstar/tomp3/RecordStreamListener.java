package com.chuanqing.youngstar.tomp3;

interface RecordStreamListener {

    void onRecording(byte[] audiodata, int x, int leng);

    void finishRecord();
}
