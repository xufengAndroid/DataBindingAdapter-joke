package com.xufeng.xfproject.presenter;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.xufeng.xfproject.contract.JokeContract;
import com.xufeng.xfproject.data.repository.JokeRepository;
import com.xufeng.xfproject.data.resq.JokeResp;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by xufeng on 2016/12/5.
 */

public class JokePresenter implements JokeContract.Presenter {

    private final JokeContract.View mTasksView;

    private final JokeRepository mJokeRepository;

    private int mPage = 1;

    public JokePresenter(@NonNull JokeRepository jokeRepository, @NonNull JokeContract.View tasksView) {
        this.mTasksView = Preconditions.checkNotNull(tasksView);
        this.mJokeRepository = Preconditions.checkNotNull(jokeRepository);
        mTasksView.setPresenter(this);

    }

    @Override
    public void start() {
        loadFirstPage();
    }


    @Override
    public void loadFirstPage() {
        mPage=1;
        mJokeRepository.getJokeContentText(mPage,20,JokeRepository.APIKEY_VAL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JokeResp>() {
                    @Override
                    public void call(JokeResp jokeResp) {
                        switch (jokeResp.error_code){
                            case 0:
                                mTasksView.showFirstPage(jokeResp.result.JokeList.size(),jokeResp.result.JokeList.size(),jokeResp.result.JokeList);
                                break;
                            default:
                                mTasksView.showPageErrMsg(jokeResp.error_code+"",jokeResp.reason);
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
        mPage++;
        mJokeRepository.getJokeContentText(mPage,20,JokeRepository.APIKEY_VAL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JokeResp>() {
                    @Override
                    public void call(JokeResp jokeResp) {
                        switch (jokeResp.error_code){
                            case 0:
                                mTasksView.showNextPage(mPage,jokeResp.result.JokeList.size(),jokeResp.result.JokeList.size(),jokeResp.result.JokeList);
                                break;
                            default:
                                mTasksView.showPageErrMsg(jokeResp.error_code+"",jokeResp.reason);
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
