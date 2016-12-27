package com.xufeng.xfproject.presenter;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.xufeng.xfproject.contract.JokeContract;
import com.xufeng.xfproject.data.repository.JokeRandRepository;
import com.xufeng.xfproject.data.resq.JokeRandResp;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by xufeng on 2016/12/5.
 */

public class JokeAllPresenter implements JokeContract.Presenter {

    private final JokeContract.View mTasksView;

    private final JokeRandRepository mJokeRandRepository;

    private int mPage = 1;

    private String type = "pic";

    public JokeAllPresenter(@NonNull JokeRandRepository jokeRandRepository, @NonNull JokeContract.View tasksView) {
        this.mTasksView = Preconditions.checkNotNull(tasksView);
        this.mJokeRandRepository = Preconditions.checkNotNull(jokeRandRepository);
        mTasksView.setPresenter(this);

    }

    @Override
    public void start() {
        loadFirstPage();
    }

    public void randType(){
//        java.util.Random random=new java.util.Random();// 定义随机类
//        int result =random.nextInt(2);// 返回[0,10)集合中的整数，注意不包括10
//        type = 0==result?"":"pic";
    }


    @Override
    public void loadFirstPage() {
        randType();
        mPage=1;
        mJokeRandRepository.getJokeRandJoke(type, JokeRandRepository.APIKEY_VAL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JokeRandResp>() {
                    @Override
                    public void call(JokeRandResp jokeRandResp) {
                        switch (jokeRandResp.error_code){
                            case 0:
                                mTasksView.showFirstPage(jokeRandResp.JokeList.size(),jokeRandResp.JokeList.size(),jokeRandResp.JokeList);
                                break;
                            default:
                                mTasksView.showPageErrMsg(jokeRandResp.error_code+"",jokeRandResp.reason);
                                break;
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        mTasksView.showPageErrMsg("E500","服务器繁忙！");

                    }
                });
    }

    @Override
    public void loadNextPage() {
        randType();
        mPage++;
        mJokeRandRepository.getJokeRandJoke(type,JokeRandRepository.APIKEY_VAL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JokeRandResp>() {
                    @Override
                    public void call(JokeRandResp jokeRandResp) {
                        switch (jokeRandResp.error_code){
                            case 0:
                                mTasksView.showNextPage(mPage,jokeRandResp.JokeList.size(),jokeRandResp.JokeList.size(),jokeRandResp.JokeList);
                                break;
                            default:
                                mTasksView.showPageErrMsg(jokeRandResp.error_code+"",jokeRandResp.reason);
                                break;
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        mTasksView.showPageErrMsg("E500","服务器繁忙！");

                    }
                });
    }


}
