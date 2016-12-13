package com.xufeng.xfproject.contract;

import java.util.List;

/**
 * Created by xufeng on 2016/12/5.
 */

public interface PageContract {

    interface View<T> {
        void showFirstPage(int pageRows,int totalRows,List<T> list);
        void showNextPage(int cruPage,int pageRows,int totalRows,List<T> list);
        void showPageErrMsg(String code,String msg);
    }


    interface Presenter{
        void loadFirstPage();
        void loadNextPage();
    }

}
