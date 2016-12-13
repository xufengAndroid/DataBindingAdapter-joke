package com.xufeng.xfproject.contract;

/**
 * Created by xufeng on 2016/12/5.
 */

public interface InitContract {

    interface View<T> {
        void showInit(T t);

        void showInitErrMsg(String errCode,String msg);
    }

    interface Presenter{
        void loadInit();
    }

}
