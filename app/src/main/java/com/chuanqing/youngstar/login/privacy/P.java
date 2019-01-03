package com.chuanqing.youngstar.login.privacy;

public class P {

    private IView privacy;

    public P(IView v){
        privacy=v;
    }

    public void setContent(){
        M m = new M();
        String getprivacy = m.getprivacy();
        privacy.setContent(getprivacy);
    }
}
